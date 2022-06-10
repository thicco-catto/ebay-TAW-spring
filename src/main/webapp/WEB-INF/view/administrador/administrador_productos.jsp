<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- 
    Document   : administrador_usuarios
    Created on : Apr 28, 2022, 10:57:33 AM
    Author     : cristobal
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.CategoriesDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.ProductsDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsersDTO admin = (UsersDTO) session.getAttribute("usuario");
    if (admin == null) {
        response.sendRedirect(request.getContextPath());
    }
%>



<html lang="en">
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
            <h1>Bienvenidx, <%=admin.getUsername()%> </h1>

            <%
                List<UsersDTO> usuarios = (List) request.getAttribute("usuarios");
                List<CategoriesDTO> categorias = (List) request.getAttribute("categorias");
            %>

            <%--@elvariable id="productFilter" type="es.taw.ebaytaw.DTO.ProductsDTO"--%>
            <form:form method="post" action="/administrador/productos/filtrar" modelAttribute="productFilter">
                Titulo: <form:input path="title"/> <br>
                Usuario: <form:select path="userID" items="${users}" itemLabel="username" itemValue="userID"/> <br>
                Categoria: <form:select path="categoryID" items="${categories}" itemLabel="name" itemValue="categoryID"/> <br>
                Precio inicial: <form:input path="initialPrice"/> <br>
                Fecha de inicio: <form:input path="startDate"/> <br>
                Fecha de fin: <form:input path="finishDate"/> <br>
                Vendido <form:select path="isSold">
                    <form:option value="">- - -</form:option>
                    <form:option value="True">Si</form:option>
                    <form:option value="False">No</form:option>
                </form:select> <br>
                <form:button>Filtrar</form:button>
            </form:form>

            <%
                List<ProductsDTO> productos = (List) request.getAttribute("productos");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                if (productos == null || productos.isEmpty()) {
            %>

            <h2>No existen productos</h2>

            <%
            } else {
            %>
            <table border="1">
                <tr>
                    <th>Titutlo</th>
                    <th>Usuario</th>
                    <th>Categoria</th>
                    <th>Descripcion</th>                
                    <th>Precio Inicial</th>                                
                    <th>Link foto</th>     
                    <th>Fecha inicio</th>                     
                    <th>Fecha final</th>
                    <th>Vendido</th>
                    <th></th>                                                     
                    <th></th>                                                                     
                </tr>

                <%
                    for (ProductsDTO producto : productos) {
                %>

                <tr>
                    <td><%= producto.getTitle()%></td>
                    <td><%= producto.getUserID().getUsername()%></td>
                    <td><%= producto.getCategoryID().getName()%></td>
                    <td><%= producto.getDescription()%></td>
                    <td><%= producto.getInitialPrice()%></td>
                    <td><%= producto.getPhoto()%></td>
                    <td><%= format.format(producto.getStartDate())%></td>
                    <td><%= format.format(producto.getFinishDate())%></td>
                    <td><%= producto.getIsSold()%></td>
                    <td><a href="AdministradorBorrarProductoServlet?id=<%= producto.getProductID()%>">Borrar</a></td>
                    <td><a href="AdministradorEditarProductoServlet?id=<%= producto.getProductID()%>">Editar</a></td>
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
