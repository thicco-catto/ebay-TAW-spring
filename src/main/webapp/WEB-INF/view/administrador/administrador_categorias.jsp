<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : administrador_usuarios
    Created on : Apr 28, 2022, 10:57:33 AM
    Author     : cristobal
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
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

        <!-- TABLA PRODUCTOS -->
        <div class="content">
            <h1>Bienvenidx, <%=admin.getUsername()%> </h1>

            <a href="/administrador/categorias/crear"><button>Nueva Categoria</button></a><br>

            <%--@elvariable id="categoryFilter" type="es.taw.ebaytaw.DTO.CategoriesDTO"--%>
            <form:form action="/administrador/categorias/filtrar" method="post" modelAttribute="categoryFilter">
                Nombre: <form:input path="name"/> <br>
                <form:button>Filtrar</form:button>
            </form:form>
    <br>
            <%
                List<CategoriesDTO> categorias = (List) request.getAttribute("categories");
                if (categorias == null || categorias.isEmpty()) {
            %>

            <h2>No existen categorias</h2>

            <%
            } else {
            %>
            <table border="1">
                <tr>
                    <th>Nombre</th>
                    <th></th>                                                     
                    <th></th>                                                                     
                </tr>

                <%
                    for (CategoriesDTO categoria : categorias) {
                %>

                <tr>
                    <td><%= categoria.getName()%></td>
                    <td><a href="/administrador/categorias/eliminar/<%= categoria.getCategoryID()%>">Borrar</a></td>
                    <td><a href="/administrador/categorias/editar/<%= categoria.getCategoryID()%>">Editar</a></td>
                </tr>

                <%
                    }
                %>

            </table>

            <%
                }
            %>
        </div>
    </body>
</html>
