package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * http://localhost/JavaWebDay15_01_Listener01/servlet/MyServlet03
 * 用来监听ServletContext域中属性变化的监听器
 */
public class MyServletContextAttributeListener01 implements ServletContextAttributeListener {
    /**
     * 添加属性
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        String n = scae.getName();
        Object v = scae.getValue();
        System.out.println("属性被加入ServletContext域"+n+"="+v);
    }

    /**
     * 移除属性
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        String n = scae.getName();
        Object v = scae.getValue();
        System.out.println("属性被移除出ServletContext域"+n+"="+v);
    }

    /**
     * 替换属性
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        String n = scae.getName();
        Object v = scae.getValue();//旧的值
        ServletContext sc = scae.getServletContext();
        Object newv = sc.getAttribute(n);//新的值
        System.out.println("属性被替换在ServletContext域"+n+"="+v+":"+newv);
    }
}
