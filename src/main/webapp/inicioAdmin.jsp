<%-- 
    Document   : inicioAdmin
    Created on : 14-oct-2023, 20:52:56
    Author     : Kevin Duran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #2c3663;">
            <jsp:include page="menu.jsp"></jsp:include>
        </nav>
            <div class="container-fluid">
                <div class="row">
                    <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar" style="background-color: yellow; height: 100vh; width: 25vh;">
                        <jsp:include page="menuAdmin.jsp"></jsp:include>
                    </nav>
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

                    </main>
                </div>
            </div>
    </body>
</html>
