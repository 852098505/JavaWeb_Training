package cn.tedu.web;

import cn.tedu.util.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ValiImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //--禁止缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        //--产生验证码输出给浏览器
        VerifyCode vc = new VerifyCode();
        vc.drawImage(response.getOutputStream());

        //--获取验证码的值
        String valistr = vc.getCode();
        //--存储到session中
        HttpSession session = request.getSession();
        session.setAttribute("valistr",valistr);

        System.out.println(valistr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
