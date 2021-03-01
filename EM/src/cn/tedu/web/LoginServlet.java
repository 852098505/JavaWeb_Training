package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserService;
import cn.tedu.utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 登录
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决乱码
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        //1.获取用户名密码
        String username = request.getParameter("username");
        String password = MD5Utils.md5(request.getParameter("password"));
        //####记住用户名
        if("true".equals(request.getParameter("remname"))){
            //--创建cookie
            Cookie remnameC = new Cookie("remnamec", URLEncoder.encode(username,"utf-8"));
            //--指定MaxAge
            remnameC.setMaxAge(60 * 60 * 24 * 30);
            //--指定Path
            remnameC.setPath(request.getContextPath());
            //--发送cookie
            response.addCookie(remnameC);
        }else{
            //--创建cookie
            Cookie remnameC = new Cookie("remnamec", "");
            //--指定MaxAge
            remnameC.setMaxAge(0);
            //--指定Path
            remnameC.setPath(request.getContextPath());
            //--发送cookie
            response.addCookie(remnameC);
        }
        //####记住用户名
        //2.调用Service完成登录
        try{
            UserService userService = new UserService();
            User user = userService.login(username,password);
            //3.登录
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //###30天内自动登录
            if("true".equals(request.getParameter("autologin"))){
                //发送cookie保存用户名密码
                String cv = URLEncoder.encode(username+"#"+password,"utf-8");
                Cookie autologinc = new Cookie("autologin",cv);
                autologinc.setMaxAge(60 * 60 * 24 * 30);//设定保存时长，30天
                autologinc.setPath(request.getContextPath());//设定访问哪个路径及其子孙路径带着cookie回来
                response.addCookie(autologinc);
            }
            //###30天内自动登录

            //3.回主页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return;
        }catch (MsgException e){
            //3.登录失败回登录页面提示
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
