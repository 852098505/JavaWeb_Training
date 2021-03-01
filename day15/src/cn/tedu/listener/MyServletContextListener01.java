package cn.tedu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener
 * 用来监听ServletContext域创建和销毁的监听器
 *
 * ServletContext域
 *  生命周期
 *      服务器启动 web应用被加载到内存 web启动
 *      随着web应用的启动ServletContext对象被创建
 *      被创建后一直存在唯一的代表当前web应用
 *      直到服务器关闭web应用被移除出容器时随着web应用的销毁而销毁
 *  作用范围
 *      整个web应用范围
 *  主要功能
 *      在web应用范围内共享数据
 *
 */
public class MyServletContextListener01 implements ServletContextListener {
    /**
     * ServetContext对象被创建时 执行此方法
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext被创建了..");
    }

    /**
     * ServetContext对象被销毁时 执行此方法
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext被销毁了..");
    }
}
