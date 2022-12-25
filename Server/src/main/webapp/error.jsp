<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
<%@ page isErrorPage="true" %>
<%
   String message = pageContext.getException().getMessage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception page</title>
</head>
<form>
<body>
<div style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 5px solid tomato; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h2 style="color:Tomato;"> Attention! </h2>
<p>Message: <%= message %></p>
</div>
</form>
</body>
</html>