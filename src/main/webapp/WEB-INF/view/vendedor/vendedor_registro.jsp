<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : registroVendedor
    Created on : 03-may-2022, 16:14:53
    Author     : mjura
--%>

<!-- MIGUEL JURADO VAZQUEZ -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro Vendedor</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;

        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Style the content */
        .content {
            background-color: #ddd;
            padding: 10px;

        }

        /* Style the footer */
        .footer {
            text-align: center;
            background-color: #f1f1f1;
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="topnav">
    <li style="float:right"><a class="active" href="/vendedor/newvendedor/toHome">Home</a></li>
</div>

<div class="content">
    <h1>Bienvenido futuro vendedor</h1>
    <p>Los campos * son obligatorios</p>

    <%--@elvariable id="user" type="es.taw.ebaytaw.DTO.UsersDTO"--%>
    <form:form method="post" action="/administrador/usuarios/guardarNuevo" modelAttribute="user">
        <table>
            <tr>
                <td>*Nombre de usuario:</td>
                <td><form:input path="username" required="true"/></td>
            </tr>

            <tr>
                <td>*Contraseña:</td>
                <td><form:input type="password" path="password" required="true"/></td>
            </tr>

            <tr>
                <td>*Email:</td>
                <td><form:input type="email" path="email" required="true"/></td>
            </tr>


        <tr>
            <td><form:input path="rol" hidden="true" value="Vendedor"/></td>
        </tr>

        <tr>
            <td>Nombre:</td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td>Apellidos:</td>
            <td><form:input path="surname"/></td>
        </tr>

        <tr>
            <td>Genero:</td>
            <td><form:select path="gender">
                <form:option value="">-</form:option>
                <form:option value="Hombre">Hombre</form:option>
                <form:option value="Mujer">Mujer</form:option>
                <form:option value="No binario">No binario</form:option>
                </form:select></td>
        </tr>

        <tr>
            <td>Numero:</td>
            <td><form:input path="number"/></td>
        </tr>

        <tr>
            <td>Calle:</td>
            <td><form:input path="street"/></td>
        </tr>

        <tr>
            <td>Ciudad:</td>
            <td><form:input path="city"/></td>
        </tr>

        <tr>
            <td>Region:</td>
            <td><form:input path="region"/></td>
        </tr>

        <tr>
            <td>Codigo postal:</td>
            <td><form:input path="postalCode"/></td>
        </tr>
        </table>
        <form:button>Crear</form:button> <br>
    </form:form>
</div>



<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>
</body>
</html>
