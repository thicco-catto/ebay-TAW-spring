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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        List<ProductsDTO> listaProductos = (List) request.getAttribute("listaProductos");
        List<CategoriesDTO> listaCategorias = (List) request.getAttribute("listaCategorias");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos en venta</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/Comprador/comprador.jsp" />
        
        <div class="content">
            <h1>Productos en venta</h1>
            <h4>Introduzca cualquier dato relativo al producto, seleccione por categorias o por productos seguidos:</h4>
            <table border="0">
                <tbody>
                    <form:form method="GET" action="ProductosEnVentaCompradorServlet">
                        <tr>

                            <td>Producto:</td>
                            <td><form:input type="text" path="filtroTituloDescripcion" value="" /></td>
                            <td><form:button class="botonazul" type="submit" value="Buscar" /></td>
                        </tr>
                        <tr>
                            <td>Categorias:</td>
                            <td>
                                <form:select path="filtroCategoria" multiple="true">
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
                        for (ProductsDTO productoDTO : listaProductos) {
                    %>
                    <tr>
                        <td>
                            <b><%= productoDTO.getTitle() %></b><br/>
                            <%= productoDTO.getDescription() %>
                        </td>
                        <td align="center"><img src="<%= productoDTO.getPhoto() %>" width="120"></td>
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
                            <%= productoDTO.lastBidPrice().getPriceBid() %> 
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
                            <img src="https://cdn-icons-png.flaticon.com/512/4051/4051030.png" width="40"> <br/>
                            <i>Adjudicado</i>
                        <%
                            } else {
                        %>
                            <img src="https://creazilla-store.fra1.digitaloceanspaces.com/emojis/53662/hourglass-done-emoji-clipart-md.png" width="40"> <br/>
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
                            <form method="GET" action="SubirPujaCompradorServlet">
                                <input type="hidden" name="productoId" value="<%= productoDTO.getProductID() %>" />
                                <input type="number" name="precioPuja" value="" size="9" step="0.01" /> <br/>
                                <input class="botonverde" type="submit" value="Subir puja" />
                            </form>
                        </td> 
                        <td>
                            <form method="GET" action="RetirarPujaCompradorServlet">
                                <input type="hidden" name="productoId" value="<%= productoDTO.getProductID() %>" />
                                <br/>
                                <input class="botonrojo" type="submit" value="Retirar puja" />
                            </form>    
                        </td>
                        <%       
                            }
                        %>
                    </tr>
                    <%
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
