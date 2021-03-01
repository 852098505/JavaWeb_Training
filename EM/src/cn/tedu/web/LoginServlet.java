package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 登录
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取用户提交的用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //###处理记住用户名
        String remname = request.getParameter("remname");
        if("true".equals(remname)){
            //用户勾选了记住用户名，发送cookie保存用户名
            Cookie remnamec = new Cookie("remnamec", URLEncoder.encode(username,"utf-8"));
            remnamec.setMaxAge(60 * 60 * 24 * 30);//保存30天
            remnamec.setPath(request.getContextPath());//访问当前应用路径及其子孙路径都要带回来
            response.addCookie(remnamec);
        }else{
            //用户没有勾选记住用户名，则删除之前发送的存储用户名的cookie
            Cookie remnamec = new Cookie("remnamec","");
            remnamec.setMaxAge(0);
            remnamec.setPath(request.getContextPath());
            response.addCookie(remnamec);
        }
        //2.查询数据库校验用户名密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //--注册数据库驱动
            //--获取数据库连接
            conn = JDBCUtils.getConn();
            //--获取传输器
            ps = conn.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            //--传输sql执行获取结果集
            rs = ps.executeQuery();
            //--获取结果数据
            if(rs.next()){
                //3.正确就登录，重定向回主页
                //--将查询到的用户信息转为user对象
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                //--获取session
                HttpSession session = request.getSession();
                //--将user对象存储到session中作为登录标记
                session.setAttribute("user",user);
                //--重定向回主页
                response.sendRedirect(request.getContextPath()+"/index.jsp");
                return;
            }else{
                //3.不正确，转发回到登录页面提示错误消息
                request.setAttribute("msg","用户名密码不正确!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            //--关闭资源
            JDBCUtils.close(conn,ps,rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
