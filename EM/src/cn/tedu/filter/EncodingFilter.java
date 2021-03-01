package cn.tedu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 全站乱码解决过滤器
 */
public class EncodingFilter implements Filter {

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encoding = config.getInitParameter("encoding");
        //1.解决请求乱码
        request.setCharacterEncoding(encoding);
        //2.解决响应乱码
        response.setContentType("text/html;charset="+encoding);
        //3.放行资源
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
