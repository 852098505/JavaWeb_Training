package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Request API
 * 获取请求头
 * http://localhost:8090/WebDay09_01_Request_API/MyServlet02
 */
public class MyServlet02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求头
        //String ua = request.getHeader("User-Agent");
        //System.out.println(ua);

        //2.获取多个同名请求头
        //Enumeration<String> enums = request.getHeaders("Host");
        //while(enums.hasMoreElements()){//还有更多元素吗？
        //    String v = enums.nextElement();//获取下一个元素
        //    System.out.println(v);
        //}

        //3.获取所有请求头的名字
        //Enumeration<String> names = request.getHeaderNames();
        //while(names.hasMoreElements()){//还有更多元素吗？
        //    String name = names.nextElement();//获取下一个元素
        //    String value = request.getHeader(name);
        //    System.out.println(name+"~"+value);
        //}

        //4.获取int类型 Date类型请求头的值
        int i = request.getIntHeader("Upgrade-Insecure-Requests");
        System.out.println(i);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
