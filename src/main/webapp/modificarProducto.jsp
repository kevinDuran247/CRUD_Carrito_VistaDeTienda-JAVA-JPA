<%-- 
    Document   : modificarProducto
    Created on : 17-oct-2023, 7:29:17
    Author     : Kevin Duran
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ModificarProducto</title>
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
                        <form method="post" action="productosserv">
                            <div class="mb-3 row">
                                <label for="inputName" class="col-4 col-form-label">ID:</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" name="idM" id="idmo"
                                           value="${productos.idproducto}" readonly>
                            </div>
                        </div>
                        <br>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Proveedor:</label>
                            <div class="col-8">
                                <select class="form-select form-select-lg" name="nombreProveedorM" id="nombreProveedor" required>
                                    <option value="${productos.idproveedor.idproveedor}">${productos.nombreProveedor}</option>
                                    <c:forEach items="${proveedores}" var="proveedores">
                                        <option value="${proveedores.idproveedor}">${proveedores.nombreProveedor}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Categoria:</label>
                            <div class="col-8">
                                <select class="form-select form-select-lg" name="nombreCategoriaM" id="nombreProveedor" required>
                                    <option value="${productos.idcategoria.idcategoria}">${productos.nombreCategoria}</option>
                                    <c:forEach items="${categorias}" var="categorias">
                                        <option value="${categorias.idcategoria}">${categorias.nombreCategoria}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Nombre producto:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="nombreProductoM"
                                       value="${productos.nombreProducto}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">URL de imagen del producto:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="imagenProductoM"
                                       value="${productos.imagen}"    required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Precio:</label>
                            <div class="col-8">
                                <input type="number" class="form-control" value="${productos.precioNormal}" name="precioProductoM" id="precio" step="0.01" min="0" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Existencias:</label>
                            <div class="col-8">
                                <input type="number" class="form-control" value="${productos.existencias}" name="existenciasProductoM" min="0" id="claveM"
                                       required>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <div class="col-sm-8 offset-sm-4 text-end">
                                <button type="submit" class="btn btn-warning">Modificar</button>
                            </div>
                        </div>

                    </form>                   
                </main>
            </div>
        </div>
    </body>
</html>
