package cn.tedu.web;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //ServletRequest请求对象
        //ServletResponse相应对象
        response.getWriter().write("hello servlet！！！！！");
    }
}
