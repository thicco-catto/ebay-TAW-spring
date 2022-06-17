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

    <form method="post" action="/vendedor/productos/crearproducto">
        <table>
            <tr>
                <td>Titulo:</td>
                <td><input required type="text" name="titulo" value=""></td>
            </tr>

            <tr>
                <td>Descripcion:</td>
                <td><input required type="text" name="descripcion" value=""></td>
            </tr>

            <tr>
                <td>Categoria:</td>
                <td><select name="categoria">
                    <%for(CategoriesDTO categoria:categorias){%>
                    <option value="<%= categoria.getName() %>"><%= categoria.getName() %></option>
                    <%}%></select></td>
            </tr>

            <tr>
                <td>Precio Inical:</td>
                <td><input required type="number" name="precioInicial" value=""></td>
            </tr>

            <tr>
                <td>Link fotografia:</td>
                <td><input required type="text" name="foto" value=""></td>
            </tr>

            <tr>
                <td>Fecha inicio:</td>
                <td><input required type="date" name="fechaInicio" value=""></td>
            </tr>

            <tr>
                <td>Fecha fin</td>
                <td><input required type="date" name="fechaFin" value=""></td>
            </tr>

        </table>
        <input type="submit" value="Crear" />
    </form>

</div>

<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>

</body>
</html>
