package example.main.java.cn.wxController;

import example.main.java.cn.utils.HttpUtils;
import example.main.java.cn.utils.ItemConfigUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author zhaoyegui
 * @date 2019-09-16 16:40
 */
public class ProjectListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HttpUtils.startTask();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
