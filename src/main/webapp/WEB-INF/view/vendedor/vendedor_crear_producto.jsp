<%--
    Document   : createProducto
    Created on : 12-may-2022, 14:51:06
    Author     : mjura
--%>

<!-- MIGUEL JURADO VAZQUEZ -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.CategoriesDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
    List<CategoriesDTO> categorias = (List)request.getAttribute("categories");
    if (vendedor == null){
        response.sendRedirect(request.getContextPath());
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Producto</title>

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
    <h5>Recuerda, todos los campos son obligatorios</h5>

    <table>
    <%--@elvariable id="product" type="es.taw.ebaytaw.DTO.ProductsDTO"--%>
    <form:form method="post" action="/vendedor/productos/crear" modelAttribute="product">
        <form:input path="userID" value="<%= vendedor.getUserID() %>" type="hidden"/>
        <tr>
            <td>Titulo:</td>
            <td><form:input path="title" required="true"/></td>
        </tr>

        <tr>
            <td>Descripcion:</td>
            <td><form:input path="description" required="true"/></td>
        </tr>
        
        <tr>
            <td>Categoria:</td>
            <td><form:select path="categoryID">
                <% for(CategoriesDTO categoria:categorias){%>
                <form:option required="true" value="<%= categoria.getName() %>"><%= categoria.getName() %></form:option>
                <%}%></form:select></td
        </tr>

        <tr>
            <td>Precio inicial:</td>
            <td><form:input path="initialPrice" required="true" /></td>
        </tr>

        <tr>
            <td>Link fotografia:</td>
            <td><form:input path="photo" required="true" /></td>
        </tr>
        <!--
        <tr>
            <td>Fecha inicio:</td>
            <td></td>
        </tr>

        <tr>
            <td>Fecha fin:</td>
            <td></td>
        </tr>
        -->
        </table>
        <form:button>Crear</form:button>
    </form:form>

</div>

<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>

</body>
</html>
