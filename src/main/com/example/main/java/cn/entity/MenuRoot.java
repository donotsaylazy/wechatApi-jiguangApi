package example.main.java.cn.entity;

import java.io.Serializable;

/**
 * @author zhaoyegui
 * @date 2019-12-18 16:07
 */
public class MenuRoot implements Serializable {

    //菜单是否开启，0代表未开启，1代表开启
    private int is_menu_open;

    //菜单信息
    private Selfmenu_info selfmenu_info;

    public void setIs_menu_open(int is_menu_open) {
        this.is_menu_open = is_menu_open;
    }
    public int getIs_menu_open() {
        return is_menu_open;
    }

    public void setSelfmenu_info(Selfmenu_info selfmenu_info) {
        this.selfmenu_info = selfmenu_info;
    }
    public Selfmenu_info getSelfmenu_info() {
        return selfmenu_info;
    }
}
