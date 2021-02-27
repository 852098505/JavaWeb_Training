package cn.tedu.web;

import cn.tedu.util.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取用户提交的请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");
        //2.校验数据
        //--校验验证码
        //TODO...
        //--用户名不能为空
        if(username==null || "".equals(username)){
            request.setAttribute("msg","用户名不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--密码不能为空
        if(password==null || "".equals(password)){
            request.setAttribute("msg","密码不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--确认密码不能为空
        if(password2==null || "".equals(password2)){
            request.setAttribute("msg","确认密码不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--昵称不能为空
        if(nickname==null || "".equals(nickname)){
            request.setAttribute("msg","昵称不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--邮箱不能为空
        if(email==null || "".equals(email)){
            request.setAttribute("msg","邮箱不能为空!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--两次密码必须一致
        if(!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--邮箱格式必须正确
        if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            request.setAttribute("msg","邮箱格式不正确!");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //3.检查用户名是否已经存在
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
            //--获取结果
            if(rs.next()){
                //--用户名已存在，提示用户
                request.setAttribute("msg","用户名已存在！");
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            //--关闭资源
            JDBCUtils.close(conn,ps,rs);
        }

        //4.将用户数据存入数据库
        try {
            //--注册数据库驱动
            //--获取数据库连接
            conn = JDBCUtils.getConn();
            //--获取传输器
            ps = conn.prepareStatement("insert into user values (null,?,?,?,?)");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,nickname);
            ps.setString(4,email);
            //--传输sql执行获取结果
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //--关闭资源
            JDBCUtils.close(conn,ps,null);
        }

        //5.回到主页
        response.getWriter().write("恭喜您注册成功，3秒后回到主页..");
        response.setHeader("refresh",
                "3;url="+request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
