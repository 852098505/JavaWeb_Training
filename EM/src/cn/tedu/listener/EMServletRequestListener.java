package cn.tedu.listener;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.Date;

@WebListener
public class EMServletRequestListener implements ServletRequestListener {

    private static Logger logger = Logger.getLogger(EMServletRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        logger.info("["+request.getRequestURI()+"]被访问");
    }
}
