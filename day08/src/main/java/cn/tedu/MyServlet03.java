package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

public class MyServlet03 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求信息
//        String uname = req.getParameter("uname");
//        String addr = req.getParameter("addr");//若遇到多个同名请求参数，只会拿第一个作为结果返回
//        System.out.println(uname);
//        System.out.println(addr);
//
//        System.out.println("########################################");
//        System.out.println(new Date().toString());

        //获取请求参数 - 多个同名请求参数处理
//        String[] addrs = req.getParameterValues("addr");
//        System.out.println(Arrays.asList(addrs));

        //遍历所有请求参数
//        Enumeration<String> names = req.getParameterNames();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            String[] values = req.getParameterValues(name);
//            System.out.println(name + "~" + Arrays.asList(values));
//        }

        //获取所有请求参数组成的map
//        Map<String, String[]> map = req.getParameterMap();
//        for (Map.Entry<String,String[]> entry: map.entrySet()) {
//            String name = entry.getKey();
//            String[] vs = entry.getValue();
//            System.out.println(name +"~"+ Arrays.asList(vs));
//        }

        //乱码处理
        //GET请求不会有乱码问题，POST请求会有乱码需要手动提交
        //告诉服务器不要用默认的iso8859-1处理实体内容，转而使用utf-8处理
        //这个代码必须放在任何获取请求参数之前
        req.setCharacterEncoding("utf-8");
        String uname = req.getParameter("uname");
        String addr = req.getParameter("addr");//若遇到多个同名请求参数，只会拿第一个作为结果返回
        System.out.println(uname);
        System.out.println(addr);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
