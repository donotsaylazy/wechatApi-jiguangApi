package example.main.java.cn.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyegui
 * @date 2019-12-18 16:09
 */
public class Button implements Serializable {

    //菜单名称
    private String name;
    //子菜单
    private List<Sub_button> sub_button;

    private String type;

    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public List<Sub_button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Sub_button> sub_button) {
        this.sub_button = sub_button;
    }
}
