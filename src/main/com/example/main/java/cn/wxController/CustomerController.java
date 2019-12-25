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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyegui
 * @date 2019-12-13 14:57
 */
@Controller
@RequestMapping("/customerController")
public class CustomerController {

    /**
     *     添加客服账户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    @ResponseBody
    public void weixin(HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> map=new HashMap<>();
        map.put("kf_account","cumsomerT1@gh_7c76ca855c69");
        map.put("nickname","郭志杰T1");
        String token="28_kw3zm2W0w7aRG_vdgRVn06yEzbND1J93yuXmDUzx81spy2-iRGAFrdpMtp9kfe-0M04o_Qbe_vdFfa7I1g9-VzftdVRjiDM3VLdtcQck47JQcxKArOrhVgXpqAoBTMhAGAXPI";
        HttpResult result = HttpUtils.sendPost(ItemConfigUtils.POST_CUSTOMER_CREATE+token, map);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result.getContent());
        if(result.getStatusCode()==200 && jsonObject.getString("errcode").equals("0") && jsonObject.getString("errmsg").equals("ok")){

            Map<String,Object> map1=new HashMap<>();
            map1.put("kf_account","cumsomerT1@gh_7c76ca855c69");
            map1.put("invite_wx","Yeahwhale");
            //绑定个人微信
            HttpResult result1 = HttpUtils.sendPost(ItemConfigUtils.POST_CUSTOMER_BIND+token
                    , map);
            System.out.println(result1);
        }


    }
}
