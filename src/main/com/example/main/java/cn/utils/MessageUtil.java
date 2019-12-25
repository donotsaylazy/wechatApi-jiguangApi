package example.main.java.cn.utils;

/**
 * @author zhaoyegui
 * @date 2019-09-16 11:25
 */
public class MessageUtil {
    /**
     * 要回复的消息
     * @param fromUser 发送方
     * @param toUser 接收方
     * @param content 回复给用户的内容
     * @return 整理好的XML文本
     * */
    public static String setMessage(String fromUser,String toUser,String content){

        return "<xml>\n" +
                "  <ToUserName>"+toUser+"</ToUserName>\n" +
                "  <FromUserName>"+fromUser+"</FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content>"+content+"</Content>\n" +
                "</xml>";
    }

}
