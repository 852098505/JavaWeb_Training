<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.tedu.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL获取数据</title>
</head>
<body>
    <h1>获取常量</h1>
    ${123}
    ${123.32}
    ${"abc"}
    ${true}

    <h1>获取变量</h1>
    <%
        pageContext.setAttribute("name","zs");
        request.setAttribute("name","ls");
    %>
    ${name}
    <h1>获取数组中的数据</h1>
    <%
        String [] addrs = new String[]{"bj","sh","gz"};
        pageContext.setAttribute("addrs",addrs);
    %>
    ${addrs[1]}
    <h1>获取集合中的数据</h1>
    <%
        List<String> list = new ArrayList<String>();
        list.add("刘备");
        list.add("关羽");
        list.add("张飞");
        pageContext.setAttribute("list",list);
    %>
    ${list[0]}
    <h1>获取Map中的数据</h1>
    <%
        Map<String,Object> map = new HashMap<>();
        map.put("name","诸葛亮");
        map.put("age","25");
        map.put("wife","黄月英");
        map.put("job",new String[]{"军师","法师","中单","dps"});
        pageContext.setAttribute("map",map);
    %>
    ${map["name"]}
    ${map["age"]}
    ${map["wife"]}
    ${map["job"][0]}

    <h1>获取javabean的属性</h1>
    <%
        User user = new User(1,"zs",18,"bj");
        pageContext.setAttribute("user",user);
    %>
    ${user.id} ${user.name} ${user.age} ${user.addr}
</body>
</html>
