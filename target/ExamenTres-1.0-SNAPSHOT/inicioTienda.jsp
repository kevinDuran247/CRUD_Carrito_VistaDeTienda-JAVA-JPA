<%-- 
    Document   : inicioTienda
    Created on : 17-oct-2023, 9:48:59
    Author     : Kevin Duran
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>TIENDA</title>
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
                    <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: lightseagreen;">
                    <jsp:include page="menuTienda.jsp"></jsp:include>
                    </nav>
                    <main>
                        <div class="container">
                            <br>

                            <div class="row">
                            <c:forEach items="${consultat}" var="productos">
                                <div class="col-md-4">
                                    <br>
                                    <div class="card">
                                        <img src="${productos.imagen}" class="card-img-top" alt="Imagen del Producto">
                                        <div class="card-body">
                                            <h5 class="card-title">${productos.nombreProducto}</h5>
                                            <p class="card-text">Existencias: ${productos.existencias}</p>
                                            <p class="card-text">Categoría: ${productos.nombreCategoria}</p>
                                            <p class="card-text">Precio: $${productos.precioNormal}</p>
                                            <p class="card-text">Proveedor: ${productos.nombreProveedor}</p>
                                            <form action="carritoserv" method="post">
                                                <input type="hidden" name="imagen" value="${productos.imagen}"> 
                                                <input type="hidden" name="idproducto" value="${productos.idproducto}"> 
                                                <input type="hidden" name="nombreProducto" value="${productos.nombreProducto}">
                                                <input type="hidden" name="precio" value="${productos.precioNormal}">
                                                <input type="hidden" name="cantidad" value="1">
                                                <input type="hidden" name="existencias" value="${productos.existencias}">
                                                <div class="col-sm-8 offset-sm-4 text-end">
                                                    <button type="submit" class="btn btn-success">Agregar al carrito</button>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </main>
            </div>
        </div>   

    </body>
</html>
