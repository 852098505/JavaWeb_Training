package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * response - 重定向
 */
public class MyServlet02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyServlet02..");

        //手工实现重定向
        //response.setStatus(302);
        //response.setHeader("Location",request.getContextPath()+"/MyServlet03");

        //便捷方法实现重定向
        response.sendRedirect(request.getContextPath()+"/MyServlet03");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
