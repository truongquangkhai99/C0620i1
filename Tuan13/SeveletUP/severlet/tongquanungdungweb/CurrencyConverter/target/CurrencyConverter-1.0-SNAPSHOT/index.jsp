<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 23/09/2020
  Time: 8:06 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency Converter</title>
    <style>
        input{
            height: 2rem;
        }
    </style>
</head>
<body>
<h2>Currency Converter</h2>
<form action="${pageContext.request.contextPath}/CurrencyConverter" method="post">
    <label>Rate: </label><br/>
    <label>
        <input type="text" name="rate" placeholder="RATE" value="22000"/>
    </label><br/>
    <label>USD: </label><br/>
    <label>
        <input type="text" name="usd" placeholder="USD" value="0"/>
    </label><br/>
    <br/>
    <input type = "submit" id = "submit" value = "Converter"/>

    <%
        float rate = Float.parseFloat(request.getParameter("rate"));
        float usd = Float.parseFloat(request.getParameter("usd"));

        float vnd = rate * usd;
    %>
    <h1>
       <%=vnd%>
    </h1>
</form>
</body>
</html>
