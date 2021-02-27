package cn.tedu.web4;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取Cookie
 */
public class MyServlet02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request中携带的所有cookie组成的数组
        Cookie[] cs = request.getCookies();
        //遍历所有Cookie
        Cookie findC = null;
        if(cs!=null){//只有当cs不为null，才可以遍历
            for(Cookie c : cs){
                //看遍历到的Cookie的名字是否是prod
                if("prod".equals(c.getName())){
                    //是，则保存此Cookie，跳出循环
                    findC = c;
                    break;
                }
            }
        }
        //判断是否找到了需要的cookie
        if(findC!=null){//findC不为null，说明循环过程中找到了需要cookie
            //获取Cookie的值，打印
            String v = findC.getValue();
            System.out.println(v);
        }else{//findC为努力了，说明循环过程中没有找到需要的cookie
            //打印信息
            System.out.println("没有这个Cookie!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
