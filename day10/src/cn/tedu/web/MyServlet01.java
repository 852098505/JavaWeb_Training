package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Response API
 */
public class MyServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置状态码
        //response.setStatus(404);

        //设置响应头
        //response.setHeader("aaa","bbb");
        //response.setHeader("aaa","ccc");
        //response.addHeader("aaa","ddd");

        //向客户端发送数据 - getOutputStream()
        //--手动设置Content-Type
        //response.setHeader("Content-Type","text/html;charset=utf-8");
        //--便捷方法设置Content-Type
        //response.setContentType("text/html;charset=utf-8");
        //OutputStream out = response.getOutputStream();
        //out.write("abc中国".getBytes("utf-8"));

        //向客户端发送数据 - getWriter()
        //response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("abc中国");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
