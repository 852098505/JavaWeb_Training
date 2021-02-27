package cn.tedu.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * ServletConfig
 */
public class MyServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletConfig对象
        ServletConfig config = getServletConfig();
        //功能1：获取Servlet的名称
        String name = config.getServletName();
        System.out.println(name);
        //功能2：获取Servlet的初始化参数
        Enumeration<String> names = config.getInitParameterNames();
        while(names.hasMoreElements()){
            String ik = names.nextElement();
            String iv = config.getInitParameter(ik);
            System.out.println(ik+"~"+iv);
        }
        //功能3：获取ServletContext对象
        ServletContext sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
