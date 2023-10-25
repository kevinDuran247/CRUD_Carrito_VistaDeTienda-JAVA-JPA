
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
        <title>LOGIN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: lightseagreen;">
            <jsp:include page="menuTienda.jsp"></jsp:include>
            </nav>
        <%
            if (lista != null) {
                double total = 0.0;
                ArrayList<Integer> id = new ArrayList<>();
                ArrayList<String> nombresProductos = new ArrayList<>();
                ArrayList<Double> precios = new ArrayList<>();
                ArrayList<Integer> cantidades = new ArrayList<>();
                ArrayList<Integer> existencias = new ArrayList<>();
                ArrayList<Double> subtotales = new ArrayList<>();

                for (int i = 0; i < lista.size(); i++) {
                    Carrito carrito = lista.get(i);
                    id.add(carrito.getIdproducto());
                    nombresProductos.add(carrito.getNombreProducto());
                    precios.add(carrito.getPrecio());
                    cantidades.add(carrito.getCantidad());
                    existencias.add(carrito.getExistencias());
                    subtotales.add(carrito.getSubtotal());

                    total += carrito.getSubtotal();
                }
        %>
        <div class="container">
            <br>
            <h3><center>INICIA SESION PARA TERMINAR LA COMPRA</center></h3>
            <br>
            <form action="comprasserv" method="post">
                <% for (int i = 0; i < lista.size(); i++) {%>
                <div>
                    <input type="hidden" name="id" value="<%= id.get(i)%>">
                    <input type="hidden" name="nombreProducto" value="<%= nombresProductos.get(i)%>">
                    <input type="hidden" name="precio" value="<%= precios.get(i)%>">
                    <input type="hidden" name="cantidad" value="<%= cantidades.get(i)%>">
                    <input type="hidden" name="existencias" value="<%= existencias.get(i)%>">
                    <input type="hidden" name="subtotal" value="<%= subtotales.get(i)%>">
                    <!-- Agrega otros campos ocultos aquí según tus datos -->
                </div>
                <% }%>
                <input type="hidden" name="total" value="<%= total%>">
                <div class="form-group">
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" class="form-control" name="user" placeholder="Ingresa tus apellidos">
                </div>
                <div class="form-group">
                    <label for="clave">Clave:</label>
                    <input type="password" class="form-control" name="password" placeholder="Ingresa tu contraseña">
                </div>
                <br>
                <input type="submit" class="btn btn-success" value="Entrar y ver factura">
            </form>
            <%
                } // Cierra el if
            %>
            <br>
            <br>
            <a href="crearCuenta.jsp"><h5>CREAR CUENTA</h5></a>
        </div>
    </body>


</html>
