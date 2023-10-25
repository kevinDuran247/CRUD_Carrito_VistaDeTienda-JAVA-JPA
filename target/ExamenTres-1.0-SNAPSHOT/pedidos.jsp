<%-- 
    Document   : pedidos
    Created on : 19-oct-2023, 19:22:31
    Author     : Kevin Duran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <br>
                    <div class="table-responsive">
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Id del cliente</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Detalles</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="">
                                    <c:forEach items="${pedidos}" var="pedidos">
                                        <td>${pedidos.idpedido}</td>
                                        <td>${pedidos.fecha}</td>
                                        <td>${pedidos.idcliente.idcliente}</td>
                                        <td>$${pedidos.total}</td>                                     
                                        <td>                                   
                                            <a href="facturaserv?idp=${pedidos.idpedido}" class="btn btn-success">
                                                Ver pedidos
                                            </a> 
                                        </td> 
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
