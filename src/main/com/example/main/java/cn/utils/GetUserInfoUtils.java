package example.main.java.cn.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

/**
 * @author zhaoyegui
 * @date 2019-09-16 20:25
 */
public class GetUserInfoUtils {

    public static void main(String [] args){
        HttpResult result = HttpUtils.sendGet(ItemConfigUtils.GET_USERINFO_URL+"25_QsA33oCKgFXMWqf0ofhvoOM1ZEX-Wo0F77B5MnbyLV4ameyLc9OmSxXQETZpSr_67_tgNwFNZIxEeZQ8wrH3WY1J8jclt1uAKm8-UFgvlX1LVko_pUT5YDZDm23cM__ioaHNmkgQzO4Nw7eASZLeAAAXEQ&openid=oqCvCwis1cAFkirw2FTlzkFZg3Vc&lang=zh_CN", null);
        System.out.println(result);
    }
}
