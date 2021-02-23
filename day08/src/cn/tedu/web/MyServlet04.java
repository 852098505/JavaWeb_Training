package cn.tedu.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发
 *  服务器内部资源跳转
 *  一次请求一次响应
 *  可以通过request域传递数据
 *  浏览器地址栏不发生变化
 *
 * 注意：
 *  可以多次转发 不要循环转发
 *  无论转发多少次，只有最后一个资源可以对外输出，其他资源的输出都会被忽略
 */
public class MyServlet04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyServlet04..");
        //转发之前存入的数据会在转发时被清空
        response.getWriter().write("data from 04..");
        //向request域中存储数据
        request.setAttribute("k1","v1");
        request.setAttribute("k2","v2");
        //转发到MyServlet05
        request.getRequestDispatcher("/MyServlet05").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
