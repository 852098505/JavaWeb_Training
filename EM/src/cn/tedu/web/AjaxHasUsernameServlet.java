package cn.tedu.web;

import cn.tedu.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * AJAX校验用户名是否已经存在
 */
public class AjaxHasUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取用户名
        String username = request.getParameter("username");
        System.out.println(username);
        //2.查询数据库，校验用户名是否存在
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //--注册数据库驱动
            //--获取数据库连接
            conn = JDBCUtils.getConn();
            //--获取传输器
            ps = conn.prepareStatement("select * from user where username = ?");
            ps.setString(1,username);
            //--传输sql执行获取结果集
            rs = ps.executeQuery();
            //--处理结果
            if(rs.next()){
                //3.提示用户：用户名已存在！
                response.getWriter().write("用户名已存在！");
            }else{
                //3.提示用户：用户名不存在！
                response.getWriter().write("用户名可用！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
