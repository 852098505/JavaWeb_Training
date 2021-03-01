package cn.tedu.filter;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

public class AutologinFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //1.未登录才做自动登录
        HttpSession session = req.getSession();
        if(session.getAttribute("user")==null){
            //2.带了自动登录cookie才做自动登录
            Cookie[] cs = req.getCookies();
            Cookie findC = null;
            if(cs!=null){
                for(Cookie c : cs){
                    if("autologin".equals(c.getName())){
                        findC = c;
                        break;
                    }
                }
            }
            if(findC!=null){
                //3.自动登录cookie中的用户名密码正确才做自动登录
                String v =  URLDecoder.decode(findC.getValue(),"utf-8");//孙悟空#123
                String username = v.split("#")[0];
                String password = v.split("#")[1];
                UserService userService = new UserService();
                try{
                    User user = userService.login(username,password);
                    //--未登录、带了自动登录cookie、自动登录cookie中的用户名密码正确，自动登录
                    session.setAttribute("user",user);
                }catch (MsgException e){
                    //--自动登录中的用户名密码不正确，不自动登录
                }

            }

        }

        //--无论是否自动登录，都放行资源
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
