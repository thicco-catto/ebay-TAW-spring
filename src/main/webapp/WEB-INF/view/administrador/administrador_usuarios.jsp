<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : administrador_usuarios
    Created on : Apr 28, 2022, 10:57:33 AM
    Author     : cristobal
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsersDTO admin = (UsersDTO) session.getAttribute("usuario");
    if (admin == null) {
        response.sendRedirect(request.getContextPath());
    }
%>



<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuarios</title>

        <style>
            * {
                box-sizing: border-box;
                font-family: Arial, Helvetica, sans-serif;
            }

            body {
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }

            /* Style the content */
            .content {
                background-color: #ddd;
                padding: 10px;
            }

            /* Style the footer */
            .footer {
                background-color: #f1f1f1;
                padding: 10px;
            }
        </style>

    </head>
    <body>
    <jsp:include page="administrador_header.jsp" />

    <!-- TABLA PRODUCTOS -->
    <div class="content">
        <h1>Bienvenidx, <%=admin.getUsername()%> </h1>

        <a href="/administrador/usuarios/crear"><button>Crear Usuario</button></a>

        <%--@elvariable id="userFilter" type="es.taw.ebaytaw.DTO.UsersDTO"--%>
        <form:form method="post" action="/administrador/usuarios/filtrar" modelAttribute="userFilter">
            Username: <form:input path="username"/> <br>
            Email: <form:input path="email"/> <br>
            Rol: <form:select path="rol">
                <form:option value="">- - -</form:option>
                <form:option value="Administrador">Administrador</form:option>
                <form:option value="Vendedor">Vendedor</form:option>
                <form:option value="Marketing">Marketing</form:option>
                <form:option value="Comprador">Comprador</form:option>
                <form:option value="Analista">Analista</form:option>
            </form:select><br>
            Nombre: <form:input path="name"/> <br>
            Apellidos: <form:input path="surname"/> <br>
            Genero: <form:select path="gender">
                <form:option value="">- - -</form:option>
                <form:option value="Hombre">Hombre</form:option>
                <form:option value="Mujer">Mujer</form:option>
                <form:option value="No binario">No binario</form:option>
            </form:select> <br>
            Numero: <form:input type="number" path="number"/> <br>
            Calle: <form:input path="street"/> <br>
            Ciudad: <form:input path="city"/> <br>
            Region: <form:input path="region"/> <br>
            Codigo postal: <form:input type="number" path="postalCode"/> <br>
            <form:button>Filtrar</form:button> <br>
        </form:form>

        <%
            List<UsersDTO> usuarios = (List) request.getAttribute("users");
            if (usuarios == null || usuarios.isEmpty()) {
        %>

        <h2>No existen usuarios</h2>

        <%
        } else {
        %>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Genero</th>
                <th>Numero</th>
                <th>Calle</th>
                <th>Ciudad</th>
                <th>Region</th>
                <th>Codigo Postal</th>
                <th></th>
                <th></th>
            </tr>

            <%
                for (UsersDTO user : usuarios) {
            %>

            <tr>
                <td><%= user.getUsername()%></td>
                <td><%= user.getEmail()%></td>
                <td><%= user.getRol()%></td>
                <td><%= user.getName()%></td>
                <td><%= user.getSurname()%></td>
                <td><%= user.getGender()%></td>
                <td><%= user.getNumber()%></td>
                <td><%= user.getStreet()%></td>
                <td><%= user.getCity()%></td>
                <td><%= user.getRegion()%></td>
                <td><%= user.getPostalCode()%></td>
                <td><a href="/administrador/usuarios/eliminar/<%= user.getUserID()%>">Borrar</a></td>
                <td><a href="/administrador/usuarios/editar/<%= user.getUserID()%>">Editar</a></td>
            </tr>

            <%
                }
            %>

        </table>

        <%
            }
        %>
    </div>
    </body>
</html>
