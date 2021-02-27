package cn.tedu.web4;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建发送Cookie
 */
public class MyServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建Cookie
        Cookie c1 = new Cookie("prod","dsj");
        //--设置超时时间
        c1.setMaxAge(60 * 60 * 24 * 30);
        //--设置Path
        c1.setPath(request.getContextPath());
        //发送Cookie
        response.addCookie(c1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
