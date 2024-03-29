<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
            <c:forEach var="entry" items="${user}">
                <tr>
                <td>${entry.getKey()}: ${entry.getValue()}<br></td>
                </tr>
            </c:forEach>
            <form action='/users/delete?id=${user.get("id")}' method="post">
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
    </body>
<!-- END -->
