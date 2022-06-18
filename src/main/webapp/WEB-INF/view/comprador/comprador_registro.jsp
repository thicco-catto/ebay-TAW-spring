<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : registroComprador
    Created on : 07-may-2022, 20:42:10
    Author     : 34637
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de nuevo comprador</title>
        
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
            <li style="float:right"><a class="active" href="/">Inicio</a></li>
            <li style="float:right"><a class="active" href="/vendedor/registro">Regístrese como vendedor</a></li>
            <li style="float:right"><a class="active" href="/comprador/registro">Regístrese como comprador</a></li>
        </div>
        
        <div class="content">
            <h1>Bienvenido, nuevo comprador</h1>
        
            <form:form method="POST" action="/comprador/registrar" modelAttribute="comprador">
                <table border="0">
                    <tbody>
                        <tr>
                            <td colspan="4"><b>Cuenta</b></td>
                        </tr>
                        <tr>
                            <form:hidden path="userID" />
                            <td>Nombre de usuario *:</td>
                            <td><form:input type="text" path="username" required="true" /></td>
                        </tr>
                        <tr>
                            <td>Correo *:</td>
                            <td><form:input type="text" path="email" required="true" /></td>
                        </tr>
                        <tr>
                            <td>Contraseña *:</td>
                            <td><form:input type="password" path="password" required="true" /></td>
                        </tr>
                        <tr>
                            <td colspan="4"><br/><b>Usuario</b></td>
                        </tr>
                        <tr>
                            <td>Nombre:</td>
                            <td><form:input type="text" path="name" /></td>
                        </tr>
                        <tr>
                            <td>Apellidos:</td>
                            <td><form:input type="text" path="surname" /></td>
                        </tr>
                        <tr>
                            <td>Sexo:</td>
                            <td>
                                <form:select path="gender">
                                    <form:option value="Hombre">Hombre</form:option>
                                    <form:option value="Mujer">Mujer</form:option>
                                    <form:option value="No Binario" selected="true">Indeterminado</form:option>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><br/><b>Domicilio</b></td>
                        </tr>
                        <tr>
                            <td>Calle:</td>
                            <td><form:input type="text" path="street" /></td>
                            <td>Número:</td>
                            <td><form:input type="number" path="number" /></td>
                        </tr>
                        <tr>
                            <td>Ciudad:</td>
                            <td><form:input type="text" path="city" /></td>
                            <td>Código postal:</td>
                            <td><form:input type="number" path="postalCode" /></td>
                        </tr>
                        <tr>
                            <td>Región:</td>
                            <td><form:input type="text" path="region" /></td>
                        </tr>
                        <tr>
                            <td colspan="4"><br/>
                                <form:button type="submit">Registrarse</form:button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
        

        
        <div class="footer">
            <p>Regístrese para formar parte de la comunidad Ebay</p>
        </div>
    </body>
</html>
