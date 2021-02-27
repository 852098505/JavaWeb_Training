package cn.tedu.web2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MyServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext
        //ServletContext sc = this.getServletConfig().getServletContext();
        ServletContext sc = this.getServletContext();

        //获取web应用的初始化信息
        Enumeration<String> names = sc.getInitParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String value = sc.getInitParameter(name);
            System.out.println(name+"~"+value);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
