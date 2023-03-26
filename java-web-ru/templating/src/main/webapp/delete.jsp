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
            <tr><td>${user.get("id")}</td></tr>
            <tr><td>${user.get("firstName")}</td></tr>
            <tr><td>${user.get("lastName")}</td></tr>
            <tr><td>${user.get("email")}</td></tr>
            <form action='/users/delete?id=${user.get("id")}' method="post">
                <button type="submit" class="btn btn-danger">Удалить</button>
            </form>
    </body>
<!-- END -->
