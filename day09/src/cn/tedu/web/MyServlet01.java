package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyServlet01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置状态码
//        resp.setStatus(123);

        //设置相响应头
 /*       resp.setHeader("aaa","bbb");
        resp.addHeader("aaa","ccc");

        //解决乱码问题
//        resp.setHeader("Content-Type","text/html;charset=utf-8");
        //便捷方法
        resp.setContentType("text/html;charset=utf-8");
        //想客户端发送数据
        ServletOutputStream out = resp.getOutputStream();
        out.write("abc中国".getBytes("utf-8"));*/

        //getwriter()
        resp.setContentType("text/html;charset=utf-8");//控制浏览器用什么解
//        resp.setCharacterEncoding("utf-8");//控制服务器用什么写
        resp.getWriter().write("abc中国");



    }
}
