<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL执行运算</title>
</head>
<body>
    <h1>算数运算</h1>
    ${2+3}
    ${2+"3"}
    <h1>关系运算</h1>
    ${3>2}
    ${3 gt 2}
    ${3 < 1}
    ${3 lt 1}
    <h1>逻辑运算</h1>
    ${3>2 && 3<1}
    ${3>2 || 3<1}
    ${!(3>2 || 3<1)}
    <h1>empty:判断对象是否非为null，字符串是否是空串，数组集合中是否不存在任何元素</h1>
    <%
        pageContext.setAttribute("user",null);
        pageContext.setAttribute("addr","");
        pageContext.setAttribute("list",new ArrayList());
    %>
    ${empty user}
    ${empty addr}
    ${empty list}
    <h1>三元运算符</h1>
    <%
        pageContext.setAttribute("age",20);
    %>
    ${age>18?"yes~":"no~"}
</body>
</html>
