<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %><%--
    Document   : administrador_nueva_categoria
    Created on : May 12, 2022, 2:47:01 PM
    Author     : cristobal
--%>

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Categorias</title>

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
            <h1>Bienvenidx, <%=admin.getUsername()%> </h1>

            <%--@elvariable id="category" type="es.taw.ebaytaw.DTO.CategoriesDTO"--%>
            <form:form action="/administrador/categorias/guardarEditado" method="post" modelAttribute="category">
                <form:hidden path="categoryID"/>
                Nombre: <form:input path="name"/> <br>
                <form:button>Editar</form:button>
            </form:form>
        </div>
    </body>
</html>
