<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : administrador_editar_producto
    Created on : May 12, 2022, 6:28:15 PM
    Author     : cristobal
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.ProductsDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.CategoriesDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsersDTO admin = (UsersDTO) session.getAttribute("usuario");
    if (admin == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Productos</title>

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

        <div class="content">
            <h1>Editar producto</h1>

            <%--@elvariable id="product" type="es.taw.ebaytaw.DTO.ProductsDTO"--%>
            <form:form method="post" action="/administrador/productos/guardarEditado" modelAttribute="product">
                <form:hidden path="productID"/>
                Titulo: <form:input path="title"/> <br>
                Descripcion: <form:input path="description"/> <br>
                Categoria: <form:select path="categoryIDint">
                <form:options items="${categories}" itemValue="categoryID" itemLabel="name"/>
            </form:select><br>
                Precio inicial: <form:input path="initialPrice"/> <br>
                Foto: <form:input path="photo"/> <br>
<%--                Fecha de inicio: <form:input type="date" path="startDate"/> <br>--%>
<%--                Fecha de fin: <form:input type="date" path="finishDate"/> <br>--%>
                Vendido <form:select path="isSold">
                <form:option value="True">Si</form:option>
                <form:option value="False">No</form:option>
            </form:select> <br>
                <form:button>Editar</form:button>
            </form:form>
        </div>
    </body>
</html>
