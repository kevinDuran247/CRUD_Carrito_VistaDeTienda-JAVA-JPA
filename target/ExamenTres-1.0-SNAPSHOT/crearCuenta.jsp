<%-- 
    Document   : crearCuenta
    Created on : 19-oct-2023, 16:22:52
    Author     : Kevin Duran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cuenta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="container">
            <form method="post" action="crearcuentaserv">
                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Nombres:</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="nombres" id="nombresM"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Apellidos:</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="apellidos" id="apellidos"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="sexo" class="col-4 col-form-label">Sexo:</label>
                    <div class="col-8">
                        <select class="form-select" name="sexo" id="sexo" required>                     
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Dirección:</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="direccionM" id="direccion"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Telefono:</label>
                    <div class="col-8">
                        <input type="tel" class="form-control" name="telefonoM" id="telefono"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">País:</label>
                    <div class="col-8">
                        <select class="form-control" name="pais" id="pais" required>
                            <option value="Desconocido">Selecciona un país</option>
                            <option value="Costa Rica">Costa Rica</option>
                            <option value="El Salvador">El Salvador</option>
                            <option value="Guatemala">Guatemala</option>
                            <option value="Honduras">Honduras</option>
                            <option value="Nicaragua">Nicaragua</option>
                            <option value="Panamá">Panamá</option>
                        </select>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Clave:</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="clave" id="clave"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="inputName" class="col-4 col-form-label">Correo:</label>
                    <div class="col-8">
                        <input type="text" class="form-control" name="correo" id="correo"
                               required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col-sm-8 offset-sm-4 text-end">
                        <button type="submit" class="btn btn-success">CREAR CUENTA</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
                integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
        </script>
    </body>
</html>
