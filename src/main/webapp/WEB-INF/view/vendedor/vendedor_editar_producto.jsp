<%--
    Document   : editProducto
    Created on : 01-may-2022, 18:07:20
    Author     : mjura
--%>

<!-- MIGUEL JURADO VAZQUEZ -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsersDTO vendedor = (UsersDTO)session.getAttribute("usuario");
    if (vendedor == null){
        response.sendRedirect(request.getContextPath());
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <form:form method="post" action="/vendedor/productos/editar" modelAttribute="product">
        <form:hidden path="productID"/>
        <tr>
            <td>Titulo:</td>
            <td><form:input path="title"/></td>
        </tr>

        <tr>
            <td>Descripcion:</td>
            <td><form:input path="description"/></td>
        </tr>

        <tr>
            <td>Categoria:</td>
            <td><form:select path="categoryIDint">
                <form:options items="${categories}" itemValue="categoryID" itemLabel="name"/>
                </form:select><br></td>
        </tr>

        <tr>
            <td>Precio inicial:</td>
            <td><form:input path="initialPrice"/></td>
        </tr>

        <tr>
            <td>Link foto:</td>
            <td><form:input path="photo"/></td>
        </tr>

            <%--
            <tr>
                <td>Fecha de inicio:</td>
                <td><form:input type="date" path="startDate"/></td>
            </tr>

            <tr>
                <td>Fecha de fin:</td>
                <td><form:input type="date" path="finishDate"/></td>
            </tr>
            --%>

        <tr>
            <td>¿Vendido?:</td>
            <td><form:select path="isSold">
                <form:option value="True">Si</form:option>
                <form:option value="False">No</form:option>
                </form:select></td>
        </tr>
    </table>
        <form:button>Editar</form:button>
    </form:form>
</div>

<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>
</body>
</html>
