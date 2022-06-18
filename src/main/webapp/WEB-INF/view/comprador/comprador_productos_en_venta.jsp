<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
    Document   : productosEnVenta
    Created on : 07-may-2022, 20:41:10
    Author     : 34637
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.ProductsDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.CategoriesDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="java.util.Objects" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        UsersDTO comprador = (UsersDTO) session.getAttribute("usuario");
        List<ProductsDTO> listaProductos = (List) request.getAttribute("listaProductos");
        List<CategoriesDTO> listaCategorias = (List) request.getAttribute("listaCategorias");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos en venta</title>
    </head>
    <body>
        <jsp:include page="comprador_header.jsp" />
        
        <div class="content">
            <h1>Productos en venta</h1>
            <h4>Introduzca cualquier dato relativo al producto, seleccione por categorias o por productos seguidos:</h4>
            <table border="0">
                <tbody>
                    <form:form method="POST" action="/comprador/productos/en-venta/filtrar" modelAttribute="filtroProductos">
                        <tr>

                            <td>Producto:</td>
                            <td><form:input type="text" path="titleAndDescription" value="" /></td>
                            <td><form:button class="botonazul" type="submit">Buscar</form:button></td>
                        </tr>
                        <tr>
                            <td>Categorias:</td>
                            <td>
                                <form:select path="categoriesArray" multiple="true">
                                    <form:option value=""></form:option>
                                    <form:options items="${listaCategorias}" itemLabel="name" itemValue="name"/>
                                </form:select>
                            </td>
                        </tr>
                    </form:form>
                </tbody>
            </table>

            <br/>
            <br/>
            
            <table class="tabla">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Foto</th>
                        <th>Fechas de inicio/fin</th>
                        <th>Precio de salida</th>
                        <th>Última puja</th>
                        <th>Estado</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (listaProductos == null || listaProductos.isEmpty()) {
                    %>
                        <tr>
                            <td align="center" colspan="8"><i>No se han encontrado productos en venta</i></td>
                        </tr>
                    <%
                        } else {
                            for (ProductsDTO productoDTO : listaProductos) {
                    %>
                        <tr>
                            <td>
                                <b><%= productoDTO.getTitle() %></b><br/>
                                <%= productoDTO.getDescription() %>
                            </td>
                            <td align="center"><img src="<%= productoDTO.getPhoto() %>" width="120" alt=""></td>
                            <td>
                                <%= productoDTO.getStartDateToString() %> <br/>
                            <%
                                if (productoDTO.getIsSold()) {
                            %>
                                <%= productoDTO.getFinishDateToString() %>
                            <%
                                } else {
                            %>
                                -
                            <%
                                }
                            %>
                            </td>
                            <td align="center"><%= productoDTO.getInitialPrice() %> €</td>
                            <td align="center">
                            <%
                                if (productoDTO.lastBidPrice() != null) {
                            %>
                                <%= productoDTO.lastBidPrice().getPrice() %>
                            <%
                                } else {
                            %>
                                -
                            <%
                                }
                            %>
                                 €
                            </td>
                            <td align="center">
                            <%
                                if (productoDTO.getIsSold()) {
                            %>
                                <img src="https://cdn-icons-png.flaticon.com/512/4051/4051030.png" width="40" alt=""> <br/>
                                <i>Adjudicado</i>
                            <%
                                } else {
                            %>
                                <img src="https://creazilla-store.fra1.digitaloceanspaces.com/emojis/53662/hourglass-done-emoji-clipart-md.png" width="40" alt=""> <br/>
                                <i>En subasta</i>
                            <%
                                }
                            %>
                            </td>
                            <%
                                if (productoDTO.getIsSold()) {
                            %>
                            <td colspan="2">
                                <i>Producto subastado</i>
                            </td>
                            <%
                                } else {
                            %>
                            <td>
                                <form:form method="GET" action="/comprador/productos/subir-puja" modelAttribute="puja">
                                    <form:input type="hidden" path="userID" value="<%= comprador.getUserID() %>" />
                                    <form:input type="hidden" path="productID" value="<%= productoDTO.getProductID() %>" />
                                    <form:input type="number" path="price" value="" size="9" step="0.01" /> <br/>
                                    <form:button class="botonverde" type="submit">Subir puja</form:button>
                                </form:form>
                            </td>
                            <td>
                                <form:form method="GET" action="/comprador/productos/retirar-puja" modelAttribute="puja">
                                    <form:input type="hidden" path="userID" value="<%= comprador.getUserID() %>" />
                                    <form:input type="hidden" path="productID" value="<%= productoDTO.getProductID() %>" />
                                    <br/>
                                    <form:button class="botonrojo" type="submit">Retirar puja</form:button>
                                </form:form>
                            </td>
                            <%
                                }
                            %>
                        </tr>
                    <%
                            }

                        }
                    %>
                </tbody>
            </table>
            
            <br/>
        </div>
        
        <div class="footer">
            <p><b>Id. de sesión:</b> <%= session.getId() %></p>
        </div>
        
    </body>
</html>
