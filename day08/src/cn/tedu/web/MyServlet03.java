package cn.tedu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * Request API
 * 获取请求参数
 * http://localhost:8090/WebDay09_01_Request_API/my03.html
 */
public class MyServlet03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        //String uname = request.getParameter("uname");
        //String addr = request.getParameter("addr");
        //System.out.println(uname);
        //System.out.println(addr);

        //2.获取请求参数 - 多个同名请求参数处理
        //String[] addrs = request.getParameterValues("addr");
        //System.out.println(Arrays.asList(addrs));

        //3.遍历所有请求参数
        //Enumeration<String> names = request.getParameterNames();//所有请求参数名组成的枚举
        //while(names.hasMoreElements()){//有更多元素吗？
        //    String name = names.nextElement();//获取下一个元素
        //    String [] vs = request.getParameterValues(name);
        //    System.out.println(name+"~"+Arrays.asList(vs));
        //}

        //4.获取所有请求参数组成的Map
        //Map<String, String[]> map = request.getParameterMap();
        //for(Map.Entry<String,String[]> entry : map.entrySet()){
        //    String name = entry.getKey();
        //    String [] vs = entry.getValue();
        //    System.out.println(name+"~"+Arrays.asList(vs));
        //}

        //5.乱码处理
        //--GET没有乱码
        //--POST需要手动解决
        //--告诉服务器不要用默认的iso8859-1处理实体内容，转而使用此处给定的编码
        //--这个代码必须放在任何获取请求参数之前，如果这个代码之前获取过任何请求参数，这个代码无效
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String addr = request.getParameter("addr");
        System.out.println(uname);
        System.out.println(addr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
