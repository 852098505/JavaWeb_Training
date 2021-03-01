package cn.tedu.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * http://localhost/JavaWebDay15_01_Listener01/servlet/MyServlet01
 * HttpSessionListener
 * 监听session对象的创建和销毁的监听器
 * Session域：
 *  生命周期:
 *      第一次调用request.getSession()时创建
 *      连续30分种没有使用销毁
 *      调用invalidate方法销毁
 *      服务器非正常关闭随着应用的销毁而销毁
 *  作用范围:
 *      一个会话范围
 *  主要功能:
 *      在一个会话中共享数据
 */
public class MyHttpSessionListener01 implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println("session被创建了..");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println("session被销毁了..");
    }
}
