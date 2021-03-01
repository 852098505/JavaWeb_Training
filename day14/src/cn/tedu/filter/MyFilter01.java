package cn.tedu.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter01 implements Filter {
    /**
     * 服务器启动或web应用初始化时 过滤器被创建
     * 创建出来之后立即调用init方法执行初始化操作
     * 之后过滤器对象一直被保留在服务器内部 为后续过滤器操作服务
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("##MyFilter01..init..");
    }

    /**
     * 在存活期间每次拦截到对资源的访问 都会造成doFilter方法的执行可以在这个方法中完成拦截逻辑
     * 过滤器默认拦截对资源的方法不允许通过
     * 可以手工调用chain.doFilter()方法来放行对资源的访问
     * 放行之后执行过滤器链中的下一个过滤器，如果没有下一个过滤器就访问到目标资源
     * @param request 代表请求的对象
     * @param response 代表响应的对象
     * @param chain 代表过滤器链的对象
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("##MyFilter01..doFilter..before");
        chain.doFilter(request,response);
        System.out.println("##MyFilter01..doFilter..after");
    }

    /**
     * 直到服务器关闭或web应用移除出容器时
     * 随着应用的销毁 过滤器对象被销毁
     * 在销毁之前 执行destory方法 进行善后工作
     */
    @Override
    public void destroy() {
        System.out.println("##MyFilter01..destory..");
    }
}
