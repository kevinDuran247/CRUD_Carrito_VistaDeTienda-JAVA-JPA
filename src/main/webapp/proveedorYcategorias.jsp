<%-- 
    Document   : proveedorYcategorias
    Created on : 15-oct-2023, 11:57:18
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
                        <div class="row">
                            <div class="col-md-6" style="background-color: lightblue; margin-bottom: 10px;">
                                <form class="mb-3" method="post" action="proveedoresycategoriasserv">
                                    <h3>PROVEEDORES</h3>
                                    <div class="mb-3">
                                        <label for="proveedor">Nombre del proveedor</label>
                                        <input type="text" class="form-control" name="nombreProveedor" id="nombreProveedor">
                                    </div>
                                    <div class="mb-3">
                                        <label for="telefono">Teléfono</label>
                                        <input type="text" class="form-control" name="telefonoProveedor" id="telefonoProveedor">
                                    </div>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </form>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre del proveedor</th>
                                            <th>Teléfono</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${consulta}" var="proveedor">
                                        <tr class="">
                                            <td scope="row">${proveedor.idproveedor}</td>
                                            <td>${proveedor.nombreProveedor}</td>
                                            <td>${proveedor.telefono}</td>                                      
                                            <td>
                                                <a href="proveedoresycategoriasserv?ide=${proveedor.idproveedor}" class="btn btn-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                         viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                         stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-trash">
                                                    <path d="M3 6h18" />
                                                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6" />
                                                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2" />
                                                    </svg>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-6" style="background-color: lightsteelblue; margin-bottom: 10px;">
                            <form class="mb-3" method="post" action="proveedoresycategoriasserv">
                                <h3>CATEGORIAS</h3>
                                <div class="mb-3">
                                    <label for "categoria"> Nombre de categoría </label>
                                    <input type="text" class="form-control" name="nombreCategoria" id="nombreCategoria">
                                </div>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </form>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ID</th> 
                                        <th>Nombre de categoría</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${consultac}" var="categoria">
                                        <tr class="">
                                            <td scope="row">${categoria.idcategoria}</td>
                                            <td>${categoria.nombreCategoria}</td>
                                    
                                            <td>
                                                <a href="proveedoresycategoriasserv?idec=${categoria.idcategoria}" class="btn btn-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                         viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                         stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-trash">
                                                    <path d="M3 6h18" />
                                                    <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6" />
                                                    <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2" />
                                                    </svg>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </main>




            </div>
        </div>
    </body>
</html>
