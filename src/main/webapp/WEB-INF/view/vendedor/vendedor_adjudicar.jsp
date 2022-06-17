<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : adjudicar
    Created on : 12-may-2022, 17:14:14
    Author     : mjura
--%>

<!-- MIGUEL JURADO VAZQUEZ -->


<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.ProductsDTO" %>
<%@ page import="es.taw.ebaytaw.entity.Users" %>
<%@ page import="es.taw.ebaytaw.entity.Bids" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
    if (vendedor == null){
        response.sendRedirect(request.getContextPath());
    }
    ProductsDTO producto = (ProductsDTO)request.getAttribute("product");
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ajudicar compra</title>

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
<jsp:include page="vendedor_header.jsp" />
<div class="content">
    <h1>Listado de pujas del producto</h1>
    <table border="2">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Precio Inicial</th>
            <th>Foto</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= producto.getTitle() %></td>
            <td><%= producto.getDescription() %></td>
            <td><%= producto.getInitialPrice() %></td>
            <td><%= producto.getPhoto() %></td>
            <td><%= format.format(producto.getStartDate()) %></td>
            <td><%= format.format(producto.getFinishDate()) %></td>
        </tr>
        </tbody>
    </table>

    <h2>Listado de pujas</h2>
    <% if(producto.getBidsList()== null || producto.getBidsList().isEmpty()){ %>
    <p>Lo siento, su producto no ha sido pujado</p>
    <%} else { %>
    <table>
        <thead>
        <tr>
            <th>Comprador</th>
            <th></th>
            <th>Puja</th>
        </tr>
        </thead>
        <tbody>
            <%BigDecimal pujaMasAlta = new BigDecimal(0);
            Users pujador = null;
                for (Bids puja:producto.getBidsList()){
                if (puja.getPrice().compareTo(pujaMasAlta) >= 1){
                    pujaMasAlta = puja.getPrice();
                    pujador = puja.getUserID();
                }%>
                <td><%= puja.getUserID().getUsername() %></td>
                <td></td>
                <td><%= puja.getPrice() %></td>
            <%}%>
        </tbody>
    </table>

    <h3>Resolución puja</h3>
    <%= vendedor.getUsername()%>, la puja mas alta ha sido de <%= pujador.getUsername() %> por <%= pujaMasAlta %> €. <br>

    ¿Confirmas la venta? <a href="/vendedor/productos/adjudicar/<%= producto.getProductID() %>">Si</a>

    <%}%>
</div>


<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>

</body>
</html>
