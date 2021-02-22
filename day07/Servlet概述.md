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

![](https://gitee.com/Cming852098505/img/raw/master/img/20210222181856.png)



#### Servlet详解

##### 		继承结构			

```
Servlet接口
		|-GenericServlet抽象类
				|-HttpServlet类
```

##### 		Servlet接口

##### 				Serlvet的根接口

```
定义了一个servlet应该具有的方法，所有的Servlet都应该直接或间接实现此接口。
```

##### 				GenericServlet抽象类

```
GenericServlet抽象类是对Servlet接口的默认实现。

对Serlvet接口中的大部分方法都做了默认实现，只有service方法是一个抽象方法需要开发者实现。

开发者只需要继承GenericServlet实现service方法，就可以在其中编写Servlet逻辑代码码。
```

##### 				HttpServlet类

```
HttpServlet类继承自GenericServlet类。

HttpServlet在GenericServlet类的基础上增加了和HTTP协议相关的属性和方法。

实现了service方法，在其中判断请求方式，根据请求方式调用对应的doXxx()方法。

开发者只需要继承HttpServlet，重写需要处理的doXxx()方法就可以完成对不同请求方式请求的处理了。
```

这是最常用的开发Servlet的方式

```
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String time = new Date().toLocaleString();
        resp.getWriter().write(time+"");
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
```

```
<servlet>
    <servlet-name>SecondServlet</servlet-name>
    <servlet-class>cn.tedu.web.SecondServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>SecondServlet</servlet-name>
    <url-pattern>/SecondServlet</url-pattern>
</servlet-mapping>
```

##### 				Servlet对外访问路径配置

###### 						基本结构

```
<servlet-mapping>
    <servlet-name>FirstServlet</servlet-name>
    <url-pattern>/FirstServlet</url-pattern>
</servlet-mapping>
```

- [ ] 一个<servlet>可以对应多个<servlet-mapping>
- [ ] 一个<servlet-mapping>中可以配置多个<url-pattern>

##### 1.通配符的使用

​			在配置<url-pattern>可以使用号通配符进行通配路径配置

​			表示匹配零个或多个任意字符

体格式为:

##### 以/开头以星号结尾的路径

```
<\url-pattern\>/ThirdServlet/</\url-pattern\>
```

##### 以.后缀结尾的路径

```
<\url-pattern\>.do</\url-pattern\>
```

##### 使用带来的路径重复匹配问题

```
Servlet1 映射到 /abc/* 
Servlet2 映射到 /* 
Servlet3 映射到 /abc 
Servlet4 映射到 *.do 
 
当请求URL为"/abc/a.html"，哪个servlet响应 ？-Servlet1
当请求URL为“/abc”时，哪个servlet响应 ？-Serlvet3
当请求URL为“/abc/a.do”时,哪个servlet响应 ？-Servlet1
当请求URL为“/a.do”时,哪个servlet响应 ？-Servelt2
当请求URL为“/xxx/yyy/a.do”时,哪个servlet响应 ？-Servelt2
```

配置的越精细，匹配度越高，优先级越高

.后缀方式，优先级最低

##### 缺省Servlet

```
将Serlvet的访问路径配置为"/",则该Servlet成为缺省Servlet
没有其他Servlet处理的请求都交由缺省Servlet来处理
通用web.xml中自带一个缺省Servlet
此缺省Servlet会在被访问时先找静态资源，找到就返回，找不到返回404页面
```

##### <load-on-startup>

```
<servlet>标签中可以配置<load-on-startup>标签，在其中配置大于0的数字
一旦配置此标签，Servlet将在服务器启动web应用加载后立即创建
数字越小，启动优先级越高
部分特殊的Servlet需要应用启动后立即存在，则可以配置此标签
```

###### 例如，缺省Serlvet就配置了此标签

```
<servlet>
    <servlet-name>default</servlet-name>
    <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
    <init-param>
        <param-name>debug</param-name>
        <param-value>0</param-value>
    </init-param>
    <init-param>
        <param-name>listings</param-name>
        <param-value>false</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
```

##### Serlvet的多线程并发安全问题

- [ ] Servlet默认机制下是单例的，存在多线程并发安全问题的风险。
- [ ] 所以，通常推荐，不要在Servlet中使用静态成员变量、普通成员变量。



### EasyMall注册-01

#### 路径专题

##### 			绝对路径

绝对于一个固定位置的路径

| <a href="file://localhost/d://aaa/bbb/ccc/ddd/1.html"></a> |
| ---------------------------------------------------------- |
| <a href="file:///d://aaa/bbb/ccc/ddd/1.html"></a>          |
| <a href="d://aaa/bbb/ccc/ddd/1.html"></a>                  |
| <a href="/aaa/bbb/ccc/ddd/1.html"></a>                     |
| <a href="http://localhost/news/aaa/bbb/1.html"></a>        |
| <a href="/news/aaa/bbb/1.html"></a>                        |

##### 			相对路径

相对于当前资源路径计算得到的路径

| ***\*源资源路径\****       | ***\*地址写法\****          | ***\*目标路径\****         |
| -------------------------- | --------------------------- | -------------------------- |
| d://aaa/bbb/ccc/ddd/1.html | <a href="2.html"></a>       | d://aaa/bbb/ccc/ddd/2.html |
| d://aaa/bbb/ccc/ddd/1.html | <a href="./2.html"></a>     | d://aaa/bbb/ccc/ddd/2.html |
| d://aaa/bbb/ccc/ddd/1.html | <a href="../2.html"></a>    | d://aaa/bbb/ccc/2.html     |
| d://aaa/bbb/ccc/ddd/1.html | <a href="../../2.html"></a> | d://aaa/bbb/2.html         |

 