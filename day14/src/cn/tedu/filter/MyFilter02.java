package cn.tedu.filter;


import javax.servlet.*;
import java.io.IOException;

public class MyFilter02 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("##MyFilter02..before..");
        chain.doFilter(request,response);
        System.out.println("##MyFilter02..after..");
    }

    @Override
    public void destroy() {

    }
}
