package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 登出
 */
@WebServlet(urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.杀死session
        HttpSession session = request.getSession();
        session.invalidate();
        //##删除30天内自动登录cookie
        Cookie autologinc = new Cookie("autologin","");
        autologinc.setPath(request.getContextPath());
        autologinc.setMaxAge(0);
        response.addCookie(autologinc);
        //##删除30天内自动登录cookie
        //2.重定向回主页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
