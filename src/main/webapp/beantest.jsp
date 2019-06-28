<%--
  Created by IntelliJ IDEA.
  User: d.klysch
  Date: 28.06.2019
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BeanTest</title>
</head>
<body>
Example for this example link:
http://localhost:8080/request?type=UserBean&format=bean&redirect=/beantest.jsp
<br>
<jsp:useBean id="bean" class="xyz.dommi.beans.UserBean" scope="request"/>
<jsp:getProperty name="bean" property="name"/>
</body>
</html>
