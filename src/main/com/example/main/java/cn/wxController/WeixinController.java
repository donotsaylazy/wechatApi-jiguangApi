package example.main.java.cn.wxController;

import example.main.java.cn.aes.AesException;
import example.main.java.cn.aes.WXBizMsgCrypt;
import example.main.java.cn.utils.ItemConfigUtils;
import example.main.java.cn.utils.MessageUtil;
import example.main.java.cn.utils.SignUtil;
import example.main.java.cn.utils.XMLUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhaoyegui
 * @date 2019-09-16 10:03
 */
@Controller
@RequestMapping("/weixinCore")
public class WeixinController {
    @RequestMapping(method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }

/*    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        //暂时空着，在这里可处理用户请求
    }*/



    /**
     *  处理交互行为
     * @param request 请求体
     * @param response 响应体
     * */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void weixin(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Map<String,String> map=new HashMap<>();
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");

      /*  Map<String, String> map = XMLUtil.getMap(request.getInputStream());
        System.out.println(map.toString());
*/
            // 微信加密签名
            String msgSignature = request.getParameter("msg_signature");
            // 时间戳
            String timeStamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");

             String encryptType = request.getParameter("encrypt_type");

             if("aes".equals(encryptType)){
                 BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                 StringBuilder sb = new StringBuilder();
                 String line;
                 try {
                     while ((line = reader.readLine()) != null) {
                         sb.append(line);
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 //密文
                 String encryptMsg = sb.toString();
                 System.out.println("未解密"+encryptMsg);
                 String data = SignUtil.decryptMsg(msgSignature, timeStamp, nonce, encryptMsg);
                 System.out.println("解密"+data);
                 try {
                     map= XMLUtil.getMap(data.getBytes(),"utf-8");
                     System.out.println(map.toString());
                     System.out.println(map.get("Content"));
                     System.out.println(map.get("ToUserName"));
                     System.out.println(map.get("FromUserName"));
                     System.out.println(map.get("MsgType"));
                     System.out.println(map.get("Event"));
                 } catch (Exception e) {
                     e.printStackTrace();
                 }


             }else{
                 //将XML转为Map
                 map = XMLUtil.getMap(request.getInputStream());
             }




        System.out.println("接受的消息:"+map.toString());

        PrintWriter writer = response.getWriter();

        //这里不要弄混了，微信推过来的信息是用户发过来的，所以ToUserName是我们的公众号，FromUserName是用户的微信openid
        //所以我们既然要回复过去，就要颠倒过来
        String fromUser = map.get("ToUserName");
        String toUser = map.get("FromUserName");
        String content = "";


        //先判断是事件消息，还是普通消息
        if (map.get("MsgType").equals("event")){
            //如果是被关注事件，向用户回复内容，只需要将整理好的XML文本参数返回给微信即可
            if (map.get("Event").equals("subscribe")){
                content = "http://e272ap.natappfree.cc/maven_test/";
            }else if(map.get("EventKey").equals("CREATE_POSTER")){
                content = "点你🐎呢¿爪巴!¿??摑?";
            }
        }else if (map.get("MsgType").equals("text")) {
            //如果是普通文本消息，先拿到用户发送过来的内容，模拟自动答疑的场景
            String text = map.get("Content");
            //普通文本消息直接给客服

        /*    if (text.equals("1")) {
                content = "看你🐎呢¿";
            } else if (text.equals("2")) {
                content = "我寻思着宁也妹买东西啊,爷搁哪找订单去?傻逼把你";
            } else if (text.equals("3")) {
                content = "你烦不烦,就你问题最多是吧?爬爬爬";
            } else {
                //否则，不管用户输入什么，都返回给ta这个列表，这也是最常见的场景
                content = "请输入您遇到的问题编号：\n" +
                        "1、如何查看退款进度？\n" +
                        "2、我的订单在哪里查看？\n" +
                        "3、其他问题";
            }*/
        }

        //把数据包返回给微信服务器，微信服务器再推给用户
        //String message=new String(MessageUtil.setMessage(fromUser,toUser,content).getBytes(),"ISO-8859-1");
        //System.out.println(MessageUtil.setMessage(fromUser,toUser,content));


        Map<String,String> responseMap=new HashMap<>();
        responseMap.put("ToUserName",toUser);
        responseMap.put("FromUserName",fromUser);
        responseMap.put("CreateTime",String.valueOf(System.currentTimeMillis()));
        responseMap.put("MsgType","transfer_customer_service");//text
        responseMap.put("Content",content);
        String replyMsg=XMLUtil.toXml(responseMap);
        System.out.println("未加密的回复:"+replyMsg);
/*        WXBizMsgCrypt pc = null;
        try {
            pc = new WXBizMsgCrypt(SignUtil.TOKEN, ItemConfigUtils.encodingAesKey, ItemConfigUtils.APPID);
        } catch (AesException e) {
            e.printStackTrace();
        }

        try {
       *//*     mingwen = pc.encryptMsg(replyMsg, String.valueOf(System.currentTimeMillis()), UUID.randomUUID().toString().replace("-", ""));*//*

        } catch (AesException e) {
            e.printStackTrace();
        }*/
        String mingwen = null;
        mingwen =  SignUtil.ecryptMsg(replyMsg,String.valueOf(System.currentTimeMillis()), UUID.randomUUID().toString().replace("-", ""));
        System.out.println("加密之后的回复："+mingwen);

        writer.print(replyMsg);
        writer.close();

    }



}
