<!-- factura.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
    <title>Factura</title>
</head>
<body>
    <h1>Detalle de la factura</h1>

    <table>
        <tr>
            <th>Nombre del Producto</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(nombresProductos)-1}">
            <tr>
                <td>${nombresProductos[i]}</td>
                <td>$${precios[i]}</td>
                <td>${cantidades[i]}</td>
                <td>$${subtotales[i]}</td>
            </tr>
        </c:forEach>
    </table>

    <p>Total: $${total}</p>
    <br>
    <a href="carritoserv?accion=vaciar&indice=0"><h4>REGRESAR</h4></a>
</body>
</html>
