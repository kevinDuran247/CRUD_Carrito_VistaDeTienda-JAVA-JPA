<%-- 
    Document   : modificarCliente
    Created on : 15-oct-2023, 10:06:30
    Author     : Kevin Duran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Examen2C</title>
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
                        <form method="post" action="clientesserv">
                            <div class="mb-3 row">
                                <label for="inputName" class="col-4 col-form-label">ID:</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" name="idmo" id="idmo"
                                           value="${cliente.idcliente}" readonly>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Nombres:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="nombresM" id="nombresM"
                                       value="${cliente.nombres}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Apellidos:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="apellidosM" id="apellidosM"
                                       value="${cliente.apellidos}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="sexo" class="col-4 col-form-label">Sexo:</label>
                            <div class="col-8">
                                <select class="form-select" name="sexoM" id="sexoM" required>
                                    <option value="${cliente.sexo}" disabled>${cliente.sexo}</option>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Femenino">Femenino</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Dirección:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="direccionM" id="direccionM"
                                       value="${cliente.direcciòn}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Telefono:</label>
                            <div class="col-8">
                                <input type="tel" class="form-control" name="telefonoM" id="telefonoM"
                                       value="${cliente.telefono}" placeholder="Ingrese numero de telefono !sin guión y sin simbolos¡" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Pais:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="paisM" id="paisM"
                                       value="${cliente.pais}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Clave:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="claveM" id="claveM"
                                       value="${cliente.clave}" required>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-4 col-form-label">Correo:</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="correoM" id="correoM"
                                       value="${cliente.correo}" required>
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








        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
                integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
        </script>
    </body>
</html>
