package cn.tedu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 用来监听session域中属性变化的监听器
 */
public class MyHttpSessionAttributeListener01 implements HttpSessionAttributeListener {
    /**
     * 增加属性
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    /**
     * 移除属性
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    /**
     * 替换属性
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
