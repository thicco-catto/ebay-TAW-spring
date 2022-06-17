<%--
    Document   : vendedor
    Created on : 27-abr-2022, 12:00:56
    Author     : mjura
--%>

<!-- MIGUEL JURADO VAZQUEZ -->
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
    if (vendedor == null){
        response.sendRedirect(request.getContextPath());
    }
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--meta http-equiv="Content-Type" content="text/html; charset=UTF-8"-->
    <title><%=vendedor.getUsername()%> page</title>

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
    </style>

</head>


<body>

<div class="topnav">
    <li style="float:right"><a href="/vendedor/productos/salir">Salir</a></li>
    <li style="float:right"><a href="/vendedor/productos">Lista de productos</a></li>
    <li style="float:right"><a href="/vendedor/productos/tocrearproducto">Nuevo producto</a></li>

</div>

<div class="content">
    <p>Session ID: <%=session.getId()%></p>
</div>

</body>
</html>
