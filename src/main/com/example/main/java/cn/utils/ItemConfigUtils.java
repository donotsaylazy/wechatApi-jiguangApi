package example.main.java.cn.utils;

/**
 * @author zhaoyegui
 * @date 2019-09-16 16:18
 */
public class ItemConfigUtils {

    //获取到的凭证
    public static volatile String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ItemConfigUtils.token = token;
    }


    /**
     * 第三方用户唯一凭证
     */
    public static final String APPID="wx65f803494a2de4f5";//

    public static final String  encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";

    /**
     * 第三方用户唯一凭证密钥
     */
    private static final String SECRET="695ee427b6ae80a569e524c195ef74df";//

    /**
     * 测试模板id
     */
    public static final String TEMPLE_ID="ZB9pMwI0PECjlBL2E6ZLRYGPxItF7Y1h8xIMJPT4_Qo";

    /**
     * 获取access_token 的微信请求地址   /GET
     */
    public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;

    /**
     * 创建菜单的接口请求地址          /POST
     */
    public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    /**
     * 获取消息模板id
     */
    public static final String GET_TEMPLE_ID_URL="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=";

    /**
     * 发送消息模板
     */
    public static final String  SEND_TEMPLE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 获取用户信息
     */
    public static final String GET_USERINFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";


    //通过code换取网页授权access_token的接口地址
    public static final String GET_USER_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+"wx65f803494a2de4f5"+"&secret="+"695ee427b6ae80a569e524c195ef74df"+"&code=";

    //根据网页授权access_token和openid换取用户信息的接口地址
    public static final String GET_CODE_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 添加客服账户
     */
     public static final String POST_CUSTOMER_CREATE="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";

    /**
     * 邀请绑定客服帐号
     */
    public static final String POST_CUSTOMER_BIND="https://api.weixin.qq.com/customservice/kfaccount/inviteworker?access_token=";

}
