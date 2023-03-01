<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="prog.utils.First" %>
<html>
<head>
  <title>Books</title>
</head>
<body>
<h1>Books</h1>
<table>
        <tHead>
            <tr>
                <th>ID</th>
                <th>Book_id</th>
                <th>Price</th>
                <th>Author_name</th>
            </tr>
        </tHead>

        <%
        First author_name = (First) request.getAttribute("author_name");
        %>
        <tr>
        <td style="text-align:center"> <%= human.getId() %> </td>
        <td style="text-align:center"> <%= human.getIdHuman() %> </td>
        <td style="text-align:center"> <%= human.getSalary() %> </td>
        <td style="text-align:center"> <%= human.getGender() %> </td>
        <tr>
</table>

<li>
<a href="findFirst"> insert id </a>
<br></br>
<a href="/prog/utils/"> back </a>
</li>
</body>
</html>