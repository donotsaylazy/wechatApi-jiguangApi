package example.main.java.cn.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyegui
 * @date 2019-12-18 16:09
 */
public class Sub_button implements Serializable {

    //菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice。使用API设置的则有8种，
    private String type;
    //菜单名称
    private String name;
    //跳转链接
    private String url;

    private List<Sub_button>  sub_button;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Sub_button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Sub_button> sub_button) {
        this.sub_button = sub_button;
    }
}
