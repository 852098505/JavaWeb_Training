package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class requestAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取客户机访问的URL
        String url = req.getRequestURL().toString();
        System.out.println(url);

        //2.获取客户机访问的资源路径
        String uri = req.getRequestURI();
        System.out.println(uri);

        //3.获取客户机访问地址中的参数信息
        //http://localhost:8080/day08_war/requestAPI?username=zs&password=123
        //返回结果为username=zs&password=123
        String qr = req.getQueryString();
        System.out.println(qr);

        //4.获取客户机的ip地址信息
        String Addr = req.getRemoteAddr();
        System.out.println(Addr);

        //5.获取客户机的请求方式
        String method = req.getMethod();
        System.out.println(method);

        //6.获取当前访问的应用名称
        String app_name = req.getContextPath();
        System.out.println(app_name);


        System.out.println("-----------------------------------");


        /*
        获取请求头信息
        * */
        String ua = req.getHeader("User-Agent");
        System.out.println(ua);

        //获取所有请求头
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String head = names.nextElement();
            String value = req.getHeader(head);
            System.out.println(head + "~" + value);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
