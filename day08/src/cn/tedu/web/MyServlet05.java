package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MyServlet05 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyServlet05..");
        //只有转发到的最后一个资源可以对外输出数据
        response.getWriter().write("data from 05..");

        //获取request域中传递数据
        //String k1 = (String) request.getAttribute("k1");
        //String k2 = (String) request.getAttribute("k2");
        //System.out.println(k1);
        //System.out.println(k2);

        //遍历request域中数据
        //Enumeration<String> names = request.getAttributeNames();
        //while(names.hasMoreElements()){
        //    String name = names.nextElement();
        //    String value = (String) request.getAttribute(name);
        //    System.out.println(name+"~"+value);
        //}

        //删除request域中的数据
        request.removeAttribute("k1");
        Object v = request.getAttribute("k1");
        System.out.println(v);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
