package example.main.java.cn.utils;



import example.main.java.cn.aes.AesException;
import example.main.java.cn.aes.WXBizMsgCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author zhaoyegui
 * @date 2019-09-16 10:42
 */
public class SignUtil {
    public static String TOKEN = "helloworld";

    /**
     * 微信生成的 ASEKey
     */
    private static String encodingAesKey ="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";


    /**
     * 验证签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }


    /**
     * 解密微信发过来的密文
     *
     * @return 加密后的内容
     */
    public static String decryptMsg(String msgSignature,String timeStamp,String nonce,String encrypt_msg) {
        WXBizMsgCrypt pc;
        String result ="";
        try {
            pc = new WXBizMsgCrypt(TOKEN, encodingAesKey, ItemConfigUtils.APPID);
            result = pc.decryptMsg(msgSignature, timeStamp, nonce, encrypt_msg);
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 加密给微信的消息内容
     * @param replayMsg
     * @param timeStamp
     * @param nonce
     * @return
     */
    public static String ecryptMsg(String replayMsg,String timeStamp, String nonce) {
        WXBizMsgCrypt pc;
        String result ="";
        try {
            pc = new WXBizMsgCrypt(TOKEN, encodingAesKey, ItemConfigUtils.APPID);
            result = pc.encryptMsg(replayMsg, timeStamp, nonce);
        } catch (AesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


}

