package cn.tedu.web;

import cn.tedu.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 获取验证码
 */
@WebServlet(urlPatterns = "/ValiImgServlet")
public class ValiImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //--禁止缓存验证码
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //--创建验证码
        VerifyCode vc = new VerifyCode();
        //--输出验证码图片到浏览器
        vc.drawImage(response.getOutputStream());
        //--验证码值存储到session
        String valistr = vc.getCode();
        System.out.println(valistr);
        HttpSession session = request.getSession();
        session.setAttribute("valistr",valistr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
