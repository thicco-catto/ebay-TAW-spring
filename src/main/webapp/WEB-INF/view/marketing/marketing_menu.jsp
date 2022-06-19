<%--
    Document   : marketing_menu
    Author     : power
--%>

<%@page import="es.taw.ebaytaw.DTO.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsersDTO marketing = (UsersDTO)session.getAttribute("usuario");
    if (marketing == null){
        response.sendRedirect(request.getContextPath());
    }
%>


<html>

<jsp:include page="/WEB-INF/view/marketing/marketing_header.jsp" />


<h1>Bienvenido  <%= marketing.getName()%></h1>

<table border="1">

    <caption><h1> Acciones </h1><caption>
        <tr>
        </tr>
        <tr>
            <form action="/marketing/crearLista/" method="get">
                <td><input type="submit" value="Crear lista de usuarios" /></td>
            </form>
            <form action="/marketing/verLista/" method="get">
                <td><input type="submit" value="Ver listas de usuarios" /></td>
            </form>
            <form action="/marketing/mensajes/<%=marketing.getUserID()%>/misMensajes" method="get">
                <td><input type="submit" value="Ver mensajes recibidos" /></td>
            </form>
            <form action="/marketing/mensajes/redactar" method="get">
                <td><input type="submit" value="Crear un nuevo mensaje" /></td>
            </form>

        </tr>


</table>



</html>
