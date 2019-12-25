package example.main.java.cn.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoyegui
 * @date 2019-12-18 16:08
 */
public class Selfmenu_info implements Serializable {

    //菜单按钮list
    private List<Button> button;

    public void setButton(List<Button> button) {
        this.button = button;
    }
    public List<Button> getButton() {
        return button;
    }
}
