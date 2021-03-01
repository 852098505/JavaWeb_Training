package cn.tedu.web;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注册
 */
@WebServlet(urlPatterns = "/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取请求参数
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String password2 = request.getParameter("password2").trim();
        String nickname = request.getParameter("nickname").trim();
        String email = request.getParameter("email").trim();
        String valistr = request.getParameter("valistr").trim();
        //2.校验数据
        //--校验验证码
        HttpSession session = request.getSession();
        String valistr2 = (String) session.getAttribute("valistr");
        if(valistr == null || valistr2 == null || !valistr.equals(valistr2)){
            request.setAttribute("msg","验证码不正确！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--校验数据
        if(username == null || "".equals(username)){
            request.setAttribute("msg","用户名不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password == null || "".equals(password)){
            request.setAttribute("msg","密码不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password2 == null || "".equals(password2)){
            request.setAttribute("msg","确认密码不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(nickname == null || "".equals(nickname)){
            request.setAttribute("msg","昵称不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(email == null || "".equals(email)){
            request.setAttribute("msg","邮箱不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            request.setAttribute("msg","邮箱格式不正确！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //3.调用service完成注册
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
        try{
            UserService userService = new UserService();
            userService.regist(user);
        }catch (MsgException e){
            //4.注册失败，回到注册页面提示
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //4.注册成功，定时刷新回主页
        response.getWriter().write("注册成功，3秒后回到主页..");
        response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
