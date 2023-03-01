<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="prog.utils.Second" %>
<html>
<head>
  <title>Titles</title>
</head>
<body>
<h1>Table Titles</h1>
<table>
        <tHead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
        </tHead>

        <%
        Second title = (Second) request.getAttribute("titles");
        %>
        <tr>
        <td style="text-align:center"> <%= human.getId() %> </td>
        <td style="text-align:center"> <%= human.getFirstName() %> </td>
        <td style="text-align:center"> <%= human.getLastName() %> </td>
        <tr>
</table>

<li>
<a href="findSecond"> insert id </a>
<br></br>
<a href="/prog/utils/"> back </a>
</li>
</body>
</html>