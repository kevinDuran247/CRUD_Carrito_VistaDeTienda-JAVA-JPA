<%-- 
    Document   : productos
    Created on : 15-oct-2023, 20:13:32
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
                        <form method="post" action="productosserv">
                            <br>
                            <div class="mb-3 row">
                                <label for="inputName" class="col-4 col-form-label">Proveedor:</label>
                                <div class="col-8">
                                    <select class="form-select form-select-lg" name="nombreProveedor" id="nombreProveedor" required>
                                    <c:forEach items="${consultap}" var="proveedor">
                                        <option value="${proveedor.idproveedor}">${proveedor.nombreProveedor}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Categoria:</label>
                            <div class="col-8">
                                <select class="form-select form-select-lg" name="nombreCategoria" id="nombreProveedor" required>
                                    <c:forEach items="${consultac}" var="categoria">
                                        <option value="${categoria.idcategoria}">${categoria.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Nombre producto:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="nombreProducto"
                                       required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">URL de imagen del producto:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="imagenProducto"
                                       required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Precio:</label>
                            <div class="col-8">
                                <input type="number" class="form-control" name="precioProducto" id="precio" step="0.01" min="0" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Existencias:</label>
                            <div class="col-8">
                                <input type="number" class="form-control" name="existenciasProducto" min="0" id="claveM"
                                       required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <div class="col-sm-8 offset-sm-4 text-end">
                                <button type="submit" class="btn btn-success">Agregar</button>
                            </div>
                        </div>

                    </form>
                    <div class="table-responsive">
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nombre producto</th>
                                    <th scope="col">Existencias</th>
                                    <th scope="col">Categoria</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Proveedor</th>
                                    <th scope="col">Imagen</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="">
                                    <c:forEach items="${consultat}" var="productos">
                                        <td>${productos.idproducto}</td>
                                        <td>${productos.nombreProducto}</td>
                                        <td>${productos.existencias}</td>
                                        <td>${productos.nombreCategoria}</td>
                                        <td>$${productos.precioNormal}</td>
                                        <td>${productos.nombreProveedor}</td>
                                        <td><img src="${productos.imagen}" alt="" class="img-thumbnail img-fluid" style="width: 95px; height: auto;"></td>

                                        <td>
                                            <a href="productosserv?idpm=${productos.idproducto}" class="btn btn-warning"> <svg
                                                    xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                    viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                    stroke-linecap="round" stroke-linejoin="round"
                                                    class="lucide lucide-pen-square">
                                                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7" />
                                                <path d="M18.5 2.5a2.12 2.12 0 0 1 3 3L12 15l-4 1 1-4Z" />
                                                </svg>
                                            </a>
                                            <a href="productosserv?idp=${productos.idproducto}" class="btn btn-danger">
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
                </main>
            </div>
        </div>
    </body>
</html>

