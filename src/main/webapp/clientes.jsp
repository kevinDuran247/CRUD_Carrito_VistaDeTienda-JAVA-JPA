<%-- 
    Document   : clientes
    Created on : 14-oct-2023, 20:36:27
    Author     : Kevin Duran
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar" style="background-color: white; height: 100vh; width: 25vh;">
                    <jsp:include page="menuAdmin.jsp"></jsp:include>
                    </nav>
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <br>
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Nombres</th>
                                        <th scope="col">Apellidos</th>
                                        <th scope="col">Sexo</th>
                                        <th scope="col">Dirección</th>
                                        <th scope="col">Telefono</th>
                                        <th scope="col">Pais</th>
                                        <th scope="col">Clave</th>
                                        <th scope="col">Correo</th>
                                        <th scope="col">Acciones</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${consulta}" var="cliente">
                                    <tr class="">
                                        <td scope="row">${cliente.idcliente}</td>
                                        <td>${cliente.nombres}</td>
                                        <td>${cliente.apellidos}</td>
                                        <td>${cliente.sexo}</td>
                                        <td>${cliente.direcciòn}</td>
                                        <td>${cliente.telefono}</td>
                                        <td>${cliente.pais}</td>
                                        <td>
                                            <div style="max-width: 100px; overflow-x: auto;">
                                                ${cliente.clave}
                                            </div>
                                        </td>
                                        <td>${cliente.correo}</td>
                                        <td>
                                            <a href="clientesserv?ide=${cliente.idcliente}" class="btn btn-danger">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                     viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                     stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-trash">
                                                <path d="M3 6h18" />
                                                <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6" />
                                                <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2" />
                                                </svg>
                                            </a>
                                            <a href="clientesserv?idm=${cliente.idcliente}" class="btn btn-warning"> <svg
                                                    xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                    viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                    stroke-linecap="round" stroke-linejoin="round"
                                                    class="lucide lucide-pen-square">
                                                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7" />
                                                <path d="M18.5 2.5a2.12 2.12 0 0 1 3 3L12 15l-4 1 1-4Z" />
                                                </svg>
                                            </a>
                                        <td>      
                                            <a href="pedidosserv?idp=${cliente.idcliente}" class="btn btn-primary">
                                                Ver pedidos
                                            </a> 
                                        </td> 
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
