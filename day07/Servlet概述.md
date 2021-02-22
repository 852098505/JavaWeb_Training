## Servlet概述

### Servlet概述

#### 		Servlet是什么

```
Servlet是sun公司提供的一门用于开发动态web资源的技术。

按照这套规范写出来的Servlet可以放置到web应用中在Servlet容器中运行。
```

#### 		开发Servlet步骤

##### 			入门案例

###### 			写一个类实现javax.Servlet接口

​				Servlet接口中定义了和Servlet开发相关的方法

| void                                                         | [destroy](#destroy())()      Called by the servlet container to indicate to a servlet that the servlet is being taken out of service.销毁方法 |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [ServletConfig](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletConfig.html) | [getServletConfig](#getServletConfig())()      Returns a [ServletConfig](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletConfig.html) object, which contains initialization and startup parameters for this servlet. |
| [String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) | [getServletInfo](#getServletInfo())()      Returns information about the servlet, such as author, version, and copyright. |
| void                                                         | [init](#init(javax.servlet.ServletConfig))([ServletConfig](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletConfig.html) config)      Called by the servlet container to indicate to a servlet that the servlet is being placed into service.初始化方法 |
| void                                                         | [service](#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse))([ServletRequest](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletRequest.html) req, [ServletResponse](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletResponse.html) res)      Called by the servlet container to allow the servlet to respond to a request服务方法，是Serlvet的核心方法，当访问Servlet时，此方法执行 |

##### 但直接实现Servlet接口开发起来比较麻烦，可以选择继承Serlvet接口的通用实现类GenericServlet

在这个抽象类中，默认实现了除service之外的方法，而将service方法定义为抽象方法

开发者只需要继承此类，实现service就可以开发出一个Servlet

```java
public class FirstServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        System.out.println("hello servlet~");
        response.getWriter().write("hello servlet~");
    }
}
```

###### 			配置Servlet

在web.xml中配置Servlet及Serlvet的访问路径

```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--配置Servlet类-->
    <servlet>
        <servlet-name>FirstServlet</servlet-name>
        <servlet-class>cn.tedu.web.FirstServlet</servlet-class>
    </servlet>
    <!--配置Servlet的访问路径-->
    <servlet-mapping>
        <servlet-name>FirstServlet</servlet-name>
        <url-pattern>/FirstServlet</url-pattern>
    </servlet-mapping>

</web-app>
```

![](https://gitee.com/Cming852098505/img/raw/master/img/20210222180121.png)

