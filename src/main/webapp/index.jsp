<%--
  Created by IntelliJ IDEA.
  User: zhaoyegui
  Date: 2019/9/18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../statics/js/jquery-3.4.1.min.js" type="text/javascript"></script>
</head>
<script>
    /**
     *
     *  获取URL中的参数
     * */
    function getUrlParam(url,name){
        var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
        var matcher = pattern.exec(url);
        var items = null;
        if(null != matcher){
            try{
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            }catch(e){
                try{
                    items = decodeURIComponent(matcher[1]);
                }catch(e){
                    items = matcher[1];
                }
            }
        }
        return items;
    }

    //app_id
    var APPID = "wx65f803494a2de4f5";
    //回调页面即是当前页
    var destUrl = decodeURIComponent(location.href);
    //微信返回来的code
    var code = getUrlParam(location.href,"code");
    //如果当前URL参数中没有code，说明用户刚进来，还没有走微信授权
    if (code==null||code==""){
        //引导用户到授权页面，这里采用静默授权，用户是无感知的
        location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+destUrl+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    }else {
        alert(code);
        //访问我们自己的接口，传入code，获取用户的信息之后展示到页面上
        $.ajax({
            url:"http://e272ap.natappfree.cc/maven_test/user/info.html",
            async:true,
            cache:false,
            dataType:"text",
            type:"post",
            success:function (str) {
                $("#headImg").attr("src",str.headimgurl);
                $("#nickName").html(str.nickname);
            },
            error:function () {
                alert("错误");
            }
        });
    }
</script>
<body>
公众号——订单页面
获取到您的基本信息：
头像：<img src="" id="headImg">
昵称：<span id="nickName"></span>
其余省略
</body>
</html>

