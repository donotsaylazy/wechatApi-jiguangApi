package example.main.java.cn.wxController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import example.main.java.cn.utils.HttpResult;
import example.main.java.cn.utils.HttpUtils;
import example.main.java.cn.utils.ItemConfigUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaoyegui
 * @date 2019-09-18 9:24
 */

@Controller
@RequestMapping("/user")
public class UserContoller {


    @RequestMapping(value = "/info",method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfo(String code){
        return JSON.parseObject(getInfoByCode(code));
    }

    public static String getInfoByCode(String code){
        HttpResult result1= HttpUtils.sendPost(ItemConfigUtils.GET_USER_TOKEN_URL+code+"&grant_type=authorization_code", "");
        System.out.println(result1);
        JSONObject jsonObject = JSON.parseObject(result1.getContent());
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");


        HttpResult result2= HttpUtils.sendPost(ItemConfigUtils.GET_CODE_INFO_URL+"?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN", "");
        System.out.println(result2);


        return result2.getContent();
    }

}
