<%--
    Document   : usuario_ver_mis_mensajes
    Created on : 14-may-2022, 16:29:08
    Author     : power
--%>

<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@ page import="es.taw.ebaytaw.entity.Mensaje" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsersDTO marketing = (UsersDTO)session.getAttribute("usuario");
    if (marketing == null){
        response.sendRedirect(request.getContextPath());
    }

%>

<%
    List<Mensaje> mensajes = (List)request.getAttribute("misMensajes");
%>



<html>

<style>
    .input,
    .textarea {
        border: 1px solid #ccc;
        font-family: inherit;
        font-size: inherit;
        padding: 1px 6px;
    }

    .textarea {
        display: block;
        width: 100%;
        overflow: hidden;
        resize: both;
        min-height: 40px;
        line-height: 20px;
    }


</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mensajes Recibidos</title>
</head>
<body>

<%--
<%if(marketing.getRol().equals("Marketing")) { %>
<jsp:include page="/WEB-INF/view/marketing/marketing_header.jsp" />
<%} else if(marketing.getRol().equals("Administrador")) { %>
<jsp:include page="/WEB-INF/view/Administrador/administrador_header.jsp" />
<%} else if(marketing.getRol().equals("Comprador")) { %>
<jsp:include page="/WEB-INF/view/Comprador/comprador.jsp" />
<%} else if(marketing.getRol().equals("Vendedor")) { %>
<jsp:include page="/WEB-INF/view/Vendedor/vendedor_header.jsp" />
<%}  %>
--%>

<jsp:include page="/WEB-INF/view/marketing/marketing_header.jsp" />



<h1>Mensajes recibidos</h1>

<table>

    <%
        if (mensajes != null && !mensajes.isEmpty())
            for (Mensaje m : mensajes) {
    %>
    <tr>
        <td><textarea readonly name="mensaje" rows="15" cols="100"><%= m.getTexto()%></textarea></td>
        <td><a href="/marketing/mensajes/<%=m.getId()%>/<%=marketing.getUserID()%>/borrar">Borrar</a></td>
    </tr>

    <%
            }
    %>


</table>
</body>
</html>