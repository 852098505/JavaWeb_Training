package cn.tedu.web;

import cn.tedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Ajax校验用户名是否已经存在
 */
@WebServlet(urlPatterns = "/servlet/AjaxHasUsernameServlet")
public class AjaxHasUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.解决乱码
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        //2.获取用户名
        String username = request.getParameter("username");
        //3.调用service检查用户名是否已经存在
        UserService userService = new UserService();
        if(userService.hasUsername(username)){
            //--存在提示"用户名已存在"
            response.getWriter().write("用户名已存在！");
        }else{
            //--不存在提示"用户名可用"
            response.getWriter().write("用户名可用！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
