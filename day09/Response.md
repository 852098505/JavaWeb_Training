# Response

## 1. Response概述

response是代表响应的对象。

 

### 1. Response继承结构

```
ServletResponse
			|-HttpServletResponse
```

1. #### ServletResponse

   ​	定义了Response应该具有的基本的方法

   #### HttpServletResponse

   ​	在ServletResponse的基础上增加了很多HTTP协议相关的方法

 

## Response功能详解

#### 		1. 设置状态码

| void | [setStatus](#setStatus(int))(int sc)      Sets the status code for this response.设置状态码 |
| ---- | :----------------------------------------------------------: |
|      |                                                              |

#### 		2. 设置响应头

| void | [setHeader](#setHeader(java.lang.String, java.lang.String))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, [String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) value)      Sets a response header with the given name and value.设置响应头，没有就增加，有就修改 |
| :--: | ------------------------------------------------------------ |
| void | [setIntHeader](#setIntHeader(java.lang.String, int))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, int value)      Sets a response header with the given name and integer value. |
| void | [setDateHeader](#setDateHeader(java.lang.String, long))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, long date)      Sets a response header with the given name and date-value. |
| void | [addHeader](#addHeader(java.lang.String, java.lang.String))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, [String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) value)      Adds a response header with the given name and value.设置响应头，没有就增加，有也是增加，多个同名头并存 |
| void | [addIntHeader](#addIntHeader(java.lang.String, int))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, int value)      Adds a response header with the given name and integer value. |
| void | [addDateHeader](#addDateHeader(java.lang.String, long))([String](http://java.sun.com/j2se/1.5/docs/api/java/lang/String.html) name, long date)      Adds a response header with the given name and date-value. |

#### 			获取输出流（写入的是response响应体里）

| [ServletOutputStream](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletOutputStream.html) | [getOutputStream](#getOutputStream())()      Returns a [ServletOutputStream](mk:@MSITStore:D:\teachdoc\java_ee_api_中英文对照版.chm::/javax/servlet/ServletOutputStream.html) suitable for writing binary data in the response.获取字节流，向客户端发送数据 |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [PrintWriter](http://java.sun.com/j2se/1.5/docs/api/java/io/PrintWriter.html) | [getWriter](#getWriter())()      Returns a PrintWriter object that can send character text to the client.获取字符流，向客户端发送数据 |

- [x] 多次获取getOutputStream()得到的是同一个流

- [x] 多次获取getWriter()得到的是同一个流

- [x] getOutputStream()和getWriter()是互斥的，在同一个response对象上只能获取一种

- [x] getOutputStream()和getWriter()不要手动关闭，Servlet容器会自动关闭此流

#### 			响应乱码解决

##### getOutputStream()

```
利用字节流输出字符数据时，需要开发人员手动将字符转为字节，此时可以手动指定输出编码。

浏览器解析时，如果不指定，默认采用操作系统码解码数据。

如果两码不一致，产生乱码。

此时可以通过设置Content-Type的响应头，告知浏览器当前数据的格式及编码，命令浏览器用指定编码打开数据，乱码解决。
```

![](https://gitee.com/Cming852098505/img/raw/master/img/20210223192034.png)

```
       //解决乱码问题
//        resp.setHeader("Content-Type","text/html;charset=utf-8");
        //便捷方法
        resp.setContentType("text/html;charset=utf-8");
        //想客户端发送数据
        ServletOutputStream out = resp.getOutputStream();
        out.write("abc中国".getBytes("utf-8"));
```

![](https://gitee.com/Cming852098505/img/raw/master/img/20210223192045.png)

##### getWriter()

利用字符流输出字符数据时，会将字符按指定编码转为字节写入response响应体，最终发送给浏览器。

此编码默认为iso8859-1,其中没有中文，输出中文时必然产生乱码。

此时可以通过response.setCharacterEncoding()；控制写入response时的编码，解决这个问题。

```
        //getwriter()
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("abc中国");
```

除此之外，如果浏览器打开数据的编码和发送编码不一致仍然会有乱码，

可以通过response.setContentType()指定浏览器打开编码，解决乱码

```
        //getwriter()
        resp.setContentType("text/html;charset=utf-8");//控制浏览器用什么解
        resp.setCharacterEncoding("utf-8");//控制服务器用什么写
        resp.getWriter().write("abc中国");
```

其实，setContentType方法，除了会设置Content-Type头，还会隐含的设置setCharacterEncoding，所以其实只需设置setContentType就可以解决响应乱码

```
        //getwriter()
        resp.setContentType("text/html;charset=utf-8");//控制浏览器用什么解
//        resp.setCharacterEncoding("utf-8");//控制服务器用什么写
        resp.getWriter().write("abc中国");
```

##### 响应乱码解决总结

```
一句话解决响应乱码，无论字节流还是字符流
resp.setContentType("text/html;charset=utf-8");
```

78:29

