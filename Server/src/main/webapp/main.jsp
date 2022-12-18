<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session= "false" pageEncoding="UTF-8" %>
    <head></head>
    <body>
          <div class="tableJoin">
          <h1>Table with join</h1>
            <table class="datatable">
              <tr>
                  <th>id </th>  <th> book_id </th>  <th> book_title </th>  <th> author_name </th>  <th> price </th>
              </tr>
                  <c:forEach var="boobsList" items="${List}">
                          <td>${boobsList.id}</td>
                          <td>${boobsList.book_id}</td>
                          <td>${boobsList.book_title}</td>
                          <td>${boobsList.author_name}</td>
                          <td>${boobsList.price}</td>
                  </c:forEach>
            </table>
         </div>
    </body>