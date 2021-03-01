package cn.tedu.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * http://localhost/JavaWebDay15_01_Listener01/servlet/MyServlet02
 * ServletRequestListener
 * 监听request域创建销毁的监听器
 * request域
 *  生命周期
 *      请求开始request对象创建，request域开始
 *      请求结束request对象销毁，request域销毁
 *  作用范围
 *      一次请求过程中
 *  主要功能
 *      在一次请求过程中共享数据
 */
public class MyServletRequestListener01 implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request被销毁..");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request被创建..");
    }
}
