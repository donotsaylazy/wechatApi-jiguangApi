package example.main.java.cn.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Synchronized;

import java.util.Date;

/**
 * @author zhaoyegui
 * @date 2019-09-16 19:16
 */

public class SendTempleMessageUtils {


    static {

        int x=5;
        System.out.println(x);
    }

    static  int x, y;


    public static void fiest(){
        System.out.println(x +"static---"+y);
        y=x++ + ++x;
        System.out.println(x +"static---"+y);
    }


    public static void main(String args []){
        System.out.println(x +"main---"+y);
       x--;
       fiest();
        System.out.println(x+y+ ++x);

 /*       JSONObject param=new JSONObject();
        //oqCvCwis1cAFkirw2FTlzkFZg3Vc
        //oqCvCwvacOIsTUcvOPsIP6sRi3wk

        //徐成 oqCvCwtlEo343K4Wj5HVOXusEVXQ
        //ting  oqCvCwnFPRmo4L2RM45X3H2uGKow
        //feng oqCvCwjjYBdZY-6dGJFmbEyVwazs
        // 祥 oqCvCwlhF-0yq5O9hVFARQqBT5jE
        param.put("touser","oqCvCwvacOIsTUcvOPsIP6sRi3wk");
        param.put("template_id","9jReB3U9x2R8XmqsrDQfL8lNIfSYMM0V_9AaOSaPCkY");
        param.put("url","http://wap.huimin888.cn/receiptStatement");




        String yesterdatStr=DateUtils.dateStr6(DateUtils.rollDay(new Date(),-1));

        JSONObject data=new JSONObject();

        JSONObject first=new JSONObject();
        first.put("value","尊敬的商户您好,"+yesterdatStr+"账单已生成!\n");   //
        data.put("first",first);
        data.put( "color","#000000");

        JSONObject keyword1=new JSONObject();
        keyword1.put("value","水果店\n");
        data.put("keyword1",keyword1);
        data.put( "color","#000000");

        JSONObject keyword2=new JSONObject();
        keyword2.put("value",yesterdatStr+"\n");
        data.put("keyword2",keyword2);
        data.put( "color","#000000");

        JSONObject remark=new JSONObject();
        remark.put("value","\n请点击详情，查看账单!");
        data.put("remark",remark);
        data.put( "color","#000000");

        param.put("data",data);

        System.out.println(param.toJSONString());

        HttpResult result1=HttpUtils.sendPost(ItemConfigUtils.SEND_TEMPLE_URL+"28_U6eBTnxFJTcv-Ty03y-QMZrzMp-hMp2py0qPtz-SPVouHBHl2TISF0XGnmV_2cdkx8o8noyV--YLpc9z9WDUcoaY9c7Yxn6IL9iclXkfhgM4hF4yrPIQy3EHCKKknqF1V569h2__4J54BavNTXAjAGABFI", param.toJSONString());
        System.out.println(result1);*/

    }
}
