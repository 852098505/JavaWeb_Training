package cn.tedu.web;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request API
 * 获取客户机信息
 * http://localhost:8090/WebDay09_01_Request_API/MyServlet01?username=zs&psw=123
 */
public class MyServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取客户机访问的URL
        String url = request.getRequestURL().toString();
        System.out.println(url);
        //2.获取客户机访问的资源路径
        String uri = request.getRequestURI();
        System.out.println(uri);
        //3.获取客户机访问地址中的参数部分
        String qr = request.getQueryString();
        System.out.println(qr);
        //4.获取客户机的ip地址信息
         String ip = request.getRemoteAddr();
        System.out.println(ip);
        //5.获取请求方式
        String method = request.getMethod();
        System.out.println(method);
        //6.获取当前应用名称
        String cp = request.getContextPath();
        System.out.println(cp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
