<%-- 
    Document   : comprador
    Created on : 07-may-2022, 20:32:25
    Author     : 34637
--%>

<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        UserDTO comprador = (UserDTO) session.getAttribute("usuario");
        
        if (comprador == null) {
            response.sendRedirect(request.getContextPath());
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprador - Página base</title>
        
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
            
            table.tabla {
                border: 1px solid;
                border-collapse: collapse;
                background-color: #f1f1f1;
            }
            
            .tabla th {
                border-top: 1px solid;
                border-bottom: 1px solid;
                background-color: #c1c1c1;
                padding: 5px 10px;
            }
            
            .tabla td {
                border-top: 1px solid;
                border-bottom: 1px solid;
                padding: 5px 10px;
            }
            
            .tabla tr:nth-child(even) {
                background-color: #e3e3e3;
            }
            
            input.botonverde { 
                background-color: #4CAF50; 
                border: 2px solid #4CAF50;
                color: white;
                padding: 8px 8px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                border-radius: 4px;
                cursor: pointer;
                transition-duration: 0.4s;
            }
            
            input.botonverde:hover {
                background-color: #5FD764;
            }
            
            input.botonrojo { 
                background-color: white; 
                color: #f44336; 
                border: 2px solid #f44336;
                padding: 8px 8px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                border-radius: 4px;
                cursor: pointer;
                transition-duration: 0.4s;
            }
            
            input.botonrojo:hover {
                background-color: #f44336;
                color: white;
            }
            
            input.botonazul { 
                background-color: #008CBA;
                cursor: pointer;
                border: 2px solid #008CBA;
                color: white;
                padding: 8px 8px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                border-radius: 4px;
                transition-duration: 0.4s;
            }
            
            input.botonazul:hover {
                background-color: #00ABE3;
            }
            
            
        </style>
    </head>
    <body>
        <div class="topnav">
            <li style="float:right"><a href="LogoutServlet">Salir</a></li>
            <li style="float:right"><a href="ProductosEnVentaCompradorServlet">Productos en venta</a></li>
            <li style="float:right"><a href="ProductosPujadosCompradorServlet">Productos pujados</a></li>
        </div>
        
        <div class="content"> 
            <h3>Bienvenido, <%= comprador.getUsername() %> </h3>
        </div>
    </body>
</html>
