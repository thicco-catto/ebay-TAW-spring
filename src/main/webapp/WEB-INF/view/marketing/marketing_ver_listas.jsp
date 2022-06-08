<%--
    Document   : marketing_ver_listas
    Created on : 14-may-2022, 14:41:04
    Author     : power
--%>
<%@page import="java.util.List"%>
<%@ page import="es.taw.ebaytaw.DTO.ListausuariosDTO" %>
<%@ page import="es.taw.ebaytaw.DTO.UsersDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UsersDTO marketing = (UsersDTO)session.getAttribute("usuario");
    if (marketing == null){
        response.sendRedirect(request.getContextPath());
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>VerListas</title>

</head>
<body>

<jsp:include page="/WEB-INF/view/marketing/marketing_header.jsp" />

<h1> Listas de Usuarios </h1>

<table border="1">

    <caption><h2> Listas </h2><caption>
            <%
            List<ListausuariosDTO> listas = (List)request.getAttribute("listas");
        %>

        <tr>
            <th>Nombre</th>
            <th></th>
        </tr>
            <%
                    if (listas != null && !listas.isEmpty())
                    for (ListausuariosDTO l : listas) {
                %>

        <tr>
            <td><%= l.getUsername()%></td>
            <td><a href="/marketing/crearLista/<%=l.getListID()%>/editarDesdeVerListas">Editar</a></td>
        </tr>

            <%
                    }
                %>


</body>
</html>
