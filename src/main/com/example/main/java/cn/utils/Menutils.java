package example.main.java.cn.utils;

import com.alibaba.fastjson.JSONObject;
import example.main.java.cn.entity.Button;
import example.main.java.cn.entity.MenuRoot;
import example.main.java.cn.entity.Selfmenu_info;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyegui
 * @date 2019-09-16 16:44
 */
public class Menutils {

    //今日热点的URL，这里跳向网易新闻
    private static final String HOTSPOT = "https://www.163.com/";
    //激流勇进的URL，跳向百度百科
    private static final String GAME = "https://baike.baidu.com/item/%E6%BF%80%E6%B5%81%E5%8B%87%E8%BF%9B/66432?fr=aladdin";
    //全民冒险的URL，这里跳到吃鸡首页
    private static final String ADVENTURE = "https://gp.qq.com/main.shtml?ADTAG=media.buy.baidukeyword.fppc_HPJY_u24796905.k121990619513.a29552693737";
    //折扣专场，跳到京东
    private static final String BUY = "https://h5.m.jd.com/pc/dev/2QurYgV498yahfXFcbmXeNuQpCyQ/index.html?unionActId=31067&d=CoY67X&s=&cu=true&utm_source=home.firefoxchina.cn&utm_medium=tuiguang&utm_campaign=t_220520384_&utm_term=8a904ba935904ef1b59178369b0faca7";
    //我的订单URL，跳向当前项目中的页面
    private static final String ORDER = "http://ryxhvp.natappfree.cc/maven_test";

    public static void main(String[] args) {
  /*      String paramStr = " {\n" +
                "     \"button\":[\n" +
                "     {\n" +
                "           \"name\":\"开通合作\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"申请智能收银设备\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/Application\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"开通电子账户\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/Loinst\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"商户权益\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/interests\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"合作申请\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/cooperation\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"收款报表\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/bindingPhone\"\n" +
                "            }\n" +
                "                      ]\n" +
                "     },\n" +
                "\n" +
                "     {\n" +
                "           \"name\":\"了解我们\",\n" +
                "           \"type\":\"view\",\n" +
                "           \"url\":\"http://wap.huimin888.cn/online\"\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"福利活动\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/welfare\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"产品介绍\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/productPresentation\"\n" +
                "            },\n" +
                "            {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"集团动态\",\n" +
                "               \"url\":\"https://mp.weixin.qq.com/mp/homepage?__biz=Mzg3NjE2NTE5Mw==&hid=1&sn=d53feff6a5bd7579c240ddbbb1d0931a&scene=18\"\n" +
                "            },\n" +
                "            {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"平安智贷\",\n" +
                "               \"url\":\"https://www.baidu.com/\"\n" +
                "            },\n" +
                "            {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"下载app\",\n" +
                "               \"url\":\"http://wap.huimin888.cn/download\"\n" +
                "            }\n" +
                "                      ]\n" +
                "       },\n" +
                "\n" +
                "     {\t\n" +
                "          \"type\":\"view\",\n" +
                "          \"name\":\"在线客服\",\n" +
                "          \"url\":\"http://wap.huimin888.cn/online\"\n" +
                "      }\n" +
                "]\n" +
                " }";
          HttpResult result=HttpUtils.sendPost(ItemConfigUtils.CREATE_MENU_URL+"28_rhtW9Cs_Wftrz8lH_KtlXUc0FYH8a1FO7oNtXdoctm_86aqefOKzBOeZctdyoRlULt6kicbRjlzNmTHRpgpunMRZfONbvxj-2bY8YARk2jEC6eZi_kDD0toBEONHXWVSgPDNpv5czqmDKdAzNXBgACAEOS", paramStr);
        System.out.println(result);*/



        HttpResult result1=HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token="+"28_oI9mfuMDy-iezMdgW8NFHDU9WlUkM7XBURNKXengCCg8gZ1M-dwI-qPOT2Qw-FqTG5kt4fHQvmv9AxMuVIf9El4oUJVegCtd6LwmV41BVGjdjOld2Hm6pQw6lqXcu-f68sDs9J05T3tEos3bAPQfAAAIXY",null);
        JSONObject jsonObject=JSONObject.parseObject(result1.getContent());
       /* Selfmenu_info test1 = jsonObject.getObject("selfmenu_info", Selfmenu_info.class);
        List<Button> test2 = test1.getButton();
        for(Button btn:test2){
            btn.getSub_button();
        }
        System.out.println(JSONObject.toJSON(test1));*/


       /* JSONObject test2 = JSONObject.parseObject(jsonObject.getString("selfmenu_info"));
        System.out.println(jsonObject.getString("selfmenu_info"));
        System.out.println();
        System.out.println(test2.getString("button"));//一级菜单
        System.out.println();
        System.out.println(result1);*/
        System.out.println(jsonObject.getString("menu"));
        System.out.println();
        System.out.println();

        System.out.println(JSONObject.parseObject(jsonObject.getString("menu")));

        System.out.println();

        JSONObject test2 = JSONObject.parseObject(jsonObject.getString("menu"));

        //Button button = test2.pa  //getObject("button", Button.class);

        List<Button> list = JSONObject.parseArray(test2.getString("button"), Button.class);

        //System.out.println(JSONObject.toJSON(button));
        System.out.println(result1);

    }

}
