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
<div style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 5px solid pink; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h2> Attention! </h2>
<p>Message: <%= message %></p>
<h2> Thank you for your attention! </h2>
</div>
</form>
</body>
</html>