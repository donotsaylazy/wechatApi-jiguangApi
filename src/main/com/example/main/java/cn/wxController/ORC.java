package example.main.java.cn.wxController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import example.main.java.cn.utils.HttpResult;
import example.main.java.cn.utils.HttpUtils;
import example.main.java.cn.utils.ItemConfigUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zhaoyegui
 * @date 2019-11-27 9:52
 */
@Controller
@RequestMapping("/orc")
public class ORC {


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfo(String code) {

        String urlString = null;
        try {
            urlString = URLEncoder.encode("http://cgyqa2.natappfree.cc/maven_test/orc.png", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String token = "27_Ttv88XzGJT7uSiYr9Mph53Mgua7su5pfSHT8PYyg7hP1FL4uhLjKHGMav_0wKdJZPgv4r24XH3p91smaIUaLwitHjGPIpxFZWi1iTiiIj64_pcnnsS-f-gc2g2bJ-vXijyah4fQk7U7nxhY4PYXeAEADML";
        HttpResult result1 = HttpUtils.sendPost("https://api.weixin.qq.com/cv/ocr/idcard?img_url=" + urlString + "&access_token=" + token, "");
        System.out.println(result1);
        JSONObject jsonObject = JSON.parseObject(result1.getContent());
        System.out.println(jsonObject.toJSONString());

        return null;
    }


    @RequestMapping("loginInit")
    @ResponseBody
    public String loginInit(HttpServletRequest request, HttpServletResponse response) {
        //回调地址,要跟下面的地址能调通(getWechatGZAccessToken.do)
        String backUrl = "http://b56gqq.natappfree.cc/maven_test/orc/getWechatGZAccessToken.html";
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        //AuthUtil.APPID微信公众号的appId
        String url = null;
        try {
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx97ceb65753c04bd3" +
                    "&redirect_uri=" + URLEncoder.encode(backUrl, "UTF-8") +
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
                    "&state=STATE#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //HttpResult result1 = HttpUtils.sendGet(url, null);
        //System.out.println(result1);
        System.out.println(url);
        return url;
    }


    @RequestMapping("getWechatGZAccessToken")
    @ResponseBody
    public String getWechatGZAccessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("111");
        //微信公众号的APPID和APPSECRET
        String code = request.getParameter("code");
        System.out.println(code);

        HttpResult result1= HttpUtils.sendPost(ItemConfigUtils.GET_USER_TOKEN_URL+code+"&grant_type=authorization_code", "");
        System.out.println(result1);
        JSONObject jsonObject = JSON.parseObject(result1.getContent());
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        System.out.println(openId);
        return  code;
    }
}