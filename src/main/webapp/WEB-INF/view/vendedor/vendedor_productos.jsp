<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.ProductsDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="es.taw.ebaytaw.DTO.CategoriesDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
    List<ProductsDTO> productos = (List)request.getAttribute("productos");
    List<CategoriesDTO> categorias = (List)request.getAttribute("categorias");
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    if (vendedor == null){
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
    <jsp:include page="vendedor_header.jsp" />
    <div class="content">
        <h1>Bienvenidx, <%=vendedor.getUsername()%> </h1>
        <!-- FILTROS -->
        <h5>Filtra tus productos:</h5>


        <!-- TABLA PRODUCTOS -->
        <%if (productos == null || productos.isEmpty()){%>
        <h2>No existen productos</h2>
        <%}else{%>
        <br>
        <h4>Sus productos:</h4>
        <table border="2">
            <tr>
                <th>Tituto</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio Inicial</th>
                <th>Link foto</th>
                <th>Fecha inicio</th>
                <th>Fecha final</th>
                <th>Vendido</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        <%for(ProductsDTO producto:productos){%>
            <tr>
                <td><%= producto.getTitle() %></td>
                <td><%= producto.getDescription() %></td>
                <td><%= producto.getCategoryID().getName() %></td>
                <td><%= producto.getInitialPrice() %> €</td>
                <td><%= producto.getPhoto() %></td>
                <td><%= format.format(producto.getStartDate())%></td>
                <td><%= format.format(producto.getFinishDate())%></td>
                <td><%= producto.getIsSold() %></td>
                <td><a href="/vendedor/productos/eliminar/<%= producto.getProductID()%>">Borrar</a></td>
                <td><a href="/vendedor/productos/toeditar/<%= producto.getProductID()%>">Editar</a></td>
                <td><% Date date = new Date();
                    if (date.after(producto.getFinishDate()) && producto.getIsSold() == false){ %>
                    <a href="/vendedor/productos/toadjudicar/<%= producto.getProductID()%>">Adjudicar</a>
                    <%}%></td>
            </tr>
        <%}%>
        </table>
        <%}%>
    </div>
    <div class="footer">
        <p>© 2022 EbayTAW, Inc</p>
    </div>
</body>
</html>