<%-- 
    Document   : carrito
    Created on : 18-oct-2023, 8:33:26
    Author     : Kevin Duran
--%>

<%@page import="modelos.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("lista") == null) {
        ArrayList<Carrito> lis = new ArrayList<Carrito>();
        session.setAttribute("lista", lis);
    }
    ArrayList<Carrito> lista = (ArrayList<Carrito>) session.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>
    <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #2c3663;">
        <jsp:include page="menu.jsp"></jsp:include>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: lightseagreen;">
                <jsp:include page="menuTienda.jsp"></jsp:include>
                </nav>
                <main>
                    <br>
                    <div class="Container">
                        <div class="table-responsive">
                            <a href="carritoserv?accion=vaciar&indice=0">Vaciar Carrito</a>


                            <table class="table">
                                <thead class="table-light">
                                    <tr>
                                        <th scope="col">Imagen</th>
                                        <th scope="col">Producto</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">SUBTOTAL</th>                          
                                        <th scope="col">QUITAR</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                    if (lista != null) {
                                        for (int i = 0; i < lista.size(); i++) {
                                            Carrito carrito = lista.get(i);
                                %>
                                <tr>
                                     <td><img src="<%= carrito.getImagen()%>" alt="" class="img-thumbnail img-fluid" style="width: 95px; height: auto;"></td>
                                    <td><%= carrito.getNombreProducto()%></td>
                                    <td>$<%= carrito.getPrecio()%></td>                                
                                    <td>
                                        <!-- Enlace para aumentar la cantidad -->
                                        <a href="carritoserv?accion=aumentar&indice=<%= i%>">MAS</a>
                                        <%= carrito.getCantidad()%>
                                        <!-- Enlace para disminuir la cantidad -->
                                        <a href="carritoserv?accion=disminuir&indice=<%= i%>">MENOS</a>
                                    </td>                                  
                                    <td>$<%= carrito.getSubtotal()%></td>
                                    <td>
                                        <!-- Enlace para eliminar el producto -->
                                        <a href="carritoserv?accion=eliminar&indice=<%= i%>">Eliminar</a>
                                    </td>


                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>

                        <h3>Total: $<%= session.getAttribute("total")%></h3> 
                        <a href="loginCliente.jsp" class="btn btn-success">COMPRAR</a>


                    </div>
                </div>
            </main>
        </div>
    </div>
</body>
</html>
