<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pageContext</title>
</head>
<body>
    <%--pageContext作为入口对象获取其他八大隐式对象--%>
    <%
        pageContext.getPage();
        pageContext.getRequest();
        pageContext.getResponse();
        pageContext.getServletConfig();
        pageContext.getServletContext();
        pageContext.getSession();
        pageContext.getOut();
        pageContext.getException();
    %>

    <%--pageContext作为入口对象操作其他三大作用域--%>
    <%
        //pageContext.setAttribute("k1","v1",PageContext.REQUEST_SCOPE);
        //pageContext.setAttribute("k1","v2",PageContext.SESSION_SCOPE);
        //pageContext.setAttribute("k1","v3",PageContext.APPLICATION_SCOPE);

        String v = (String) pageContext.findAttribute("k1");
        System.out.println(v);
    %>
</body>
</html>
