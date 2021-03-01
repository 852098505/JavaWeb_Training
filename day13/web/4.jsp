<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL的内置对象</title>
</head>
<body>
    <h1>pageContext</h1>
    ${pageContext.request.contextPath}
    ${pageContext.session.id}

    <h1>代表四大作用域的对象</h1>
    <%
        pageContext.setAttribute("name","zl");
        request.setAttribute("name","zs");
        session.setAttribute("name","ls");
        application.setAttribute("name","ww");
    %>
    ${requestScope.name}
    ${sessionScope.name}
    ${applicationScope.name}
    ${pageScope.name}

    <h1>代表请求参数值的对象</h1>
    ${param.username}
    ${param.age}
    ${paramValues.like[0]}
    ${paramValues.like[1]}
    ${paramValues.like[2]}

    <h1>代表请求头的对象</h1>
    ${header.Cookie}
    ${header["Accept-Encoding"]}

    <h1>代表Cookie对象</h1>
    ${cookie.JSESSIONID.value}

    <h1>代表应用初始化参数的对象</h1>
    ${initParam.ck1}
    ${initParam.ck2}


</body>
</html>
