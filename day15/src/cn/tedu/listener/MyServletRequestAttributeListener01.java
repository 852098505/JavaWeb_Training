package cn.tedu.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * 监听request域属性变化的监听器
 */
public class MyServletRequestAttributeListener01 implements ServletRequestAttributeListener {
    /**
     * 增加属性
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    /**
     * 移除属性
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    /**
     * 替换属性
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }
}
