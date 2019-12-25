package example.main.java.cn.jpush.api;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import example.main.java.cn.utils.XMLUtil;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.jcajce.spec.TLSKeyMaterialSpec.MASTER_SECRET;

/**
 * @author zhaoyegui
 * @date 2019-08-26 10:03
 */
public class test {

    protected static final String APP_KEY = "48182495c534bb36cb35bad8";
    protected static final String MASTER_SECRET = "f81989ae00edecf6b1e29b14";


    public static void jpushAndroid(Map<String, String> parm) {

        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                //.setAudience(Audience.all())//你项目中的所有用户
//                .setAudience(Audience.alias(parm.get("alias")))//设置别名发送,单发，点对点方式
                //.setAudience(Audience.tag("tag1"))//设置按标签发送，相当于群发
                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户

                .setNotification(Notification.android(parm.get("msg"), parm.get("title"), parm))  //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(7200).build())
                // apnProduction指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式) 不用设置也没关系
                // TimeToLive 两个小时的缓存时间
                .setMessage(Message.content(parm.get("msg")))//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }


    //极光推送>>ios
    //Map<String, String> parm是我自己传过来的参数,可以自定义参数
    public static  void jpushIOS(Map<String, String> parm) {

        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())//ios平台的用户
                //.setAudience(Audience.all())//所有用户
                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(parm.get("msg"))
                                .setBadge(+1)
                                .setSound("happy")//这里是设置提示音(更多可以去官网看看)
                                .addExtras(parm)
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }




    //极光推送>>All所有平台
    public static void jpushAll(Map<String, String> parm,ArrayList<String> list) {
        ClientConfig clientConfig = ClientConfig.getInstance();

        //创建JPushClient
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        //创建option
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())  //所有平台的用户
                //.setAudience(Audience.all())
                .setAudience(Audience.registrationId(list))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder() //发送ios
                                .setAlert(parm.get("msg")) //消息体
                                .setBadge(+1)
                                .setMutableContent(true)
                                .setContentAvailable(true)
                                //ios提示音.setSound("happy")
                                .addExtra("msgPointMoney",parm.get("msgPointMoney"))
                                .addExtra("msgMoney",parm.get("msgMoney"))
                                .addExtra("msgStr",parm.get("msgStr")) //附加参数
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder() //发送android
                                .setAlert(parm.get("msg")) //消息体
                                .addExtra("msgPointMoney",parm.get("msgPointMoney"))
                                .addExtra("msgMoney",parm.get("msgMoney"))
                                .addExtra("msgStr",parm.get("msgStr")) //附加参数
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())//指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                //.setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            System.out.println("Connection error. Should retry later"+ e);
        } catch (APIRequestException e) {
            System.out.println("Error response from JPush server. Should review and fix it. "+ e);
            System.out.println("HTTP Status: "+ e.getStatus());
            System.out.println("Error Code: "+ e.getErrorCode());
            System.out.println("Error Message:"+ e.getErrorMessage());
            System.out.println("Msg ID: "+ e.getMsgId());
        }
    }



        public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(Map<String, String> parm,ArrayList<String> list) {
            return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(list))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                        .setAlert(parm.get("msg"))
                        .addExtras(parm)
                        .build())
                        .build())
                .build();
                /*.setMessage(Message.newBuilder()
                        .setMsgContent(parm.get("msg"))
                        .setTitle(parm.get("title"))
                        .addExtras(parm)
                        .build())*/
    }


    public static void main(String[] args) {
        //设置推送参数
        //这里可以自定义推送参数了


        Map<String,String> responseMap=new HashMap<>();
        responseMap.put("ToUserName","test");
        responseMap.put("FromUserName","code");
        responseMap.put("CreateTime",String.valueOf(System.currentTimeMillis()));
        responseMap.put("MsgType","text");
        responseMap.put("Content","嘻嘻嘻哈哈");
        System.out.println(XMLUtil.toXml(responseMap));



    /*    Map<String, String> parm = new HashMap<String, String>();
        //设置提示信息,内容是文章标题
        parm.put("msg","支付宝收款10541523435元");
        parm.put("msgMoney","伍万陆千柒佰捌拾玖元");
        parm.put("msgPointMoney","12");
        parm.put("msgStr","支付宝收款");*/


        //parm.put("alias","abc");

/*        ArrayList<String> list=new ArrayList<>();
        list.add("18171adc03702d1da60");
        list.add("101d85590924737390f");

        jpushAll(parm,list);*/


/*      BigDecimal a=new BigDecimal("-12.99");
        String aa=a.toString();
        BigDecimal b=new BigDecimal("12.00");
        String bb=b.toString();
        BigDecimal c=new BigDecimal("0.05");
        String cc=c.toString();
        BigDecimal adsfa = b.setScale(0,BigDecimal.ROUND_DOWN);
        System.out.println(adsfa);
        System.out.println(b.subtract(adsfa));

        int  index=c.toString().indexOf(".");
        System.out.println(index);
        if(-1!=index){
            String integerPart=cc.substring(0,index);
            String lastPart=cc.substring(index+1,cc.length());
            System.out.println(integerPart +" ----- "+ lastPart);
        }*/

   /*     String test="alipay1234";//"wxinfdafds";//
        if("wxin".contains(test)){
            System.out.println(123);
        }else if("alipay".contains(test)){
            System.out.println(456);
        }*/


        //parm.put("id","170976fa8ae065f5c0d");
        //jpushAndroid(parm);

       // testSendPushWithCallback();
    }



    public static void testSendPushWithCallback() {


        Map<String, String> parm = new HashMap<String, String>();
        //设置提示信息,内容是文章标题
        parm.put("msg","异步测试");

        parm.put("title", "异步测试");
        //parm.put("alias","abc");

        ArrayList<String> list=new ArrayList<>();
        list.add("18171adc03702d1da60");
        list.add("170976fa8ae065f5c0d");

        ClientConfig clientConfig = ClientConfig.getInstance();
        System.out.println(ClientConfig.PUSH_HOST_NAME);
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        final NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET),
                null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(parm,list);
            System.out.println("推送数据"+ payload.toString());
            System.out.println("推送地址"+ uri);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    System.out.println("Got result: "+ responseWrapper);
                    System.out.println("Got result: "+ responseWrapper.toString());
                    System.out.println("Got result: "+ responseWrapper.responseContent);
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
