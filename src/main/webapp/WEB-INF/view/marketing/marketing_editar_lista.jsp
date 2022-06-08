<%--
    Document   : marketing_editar_lista
    Author     : power
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

<style type='text/css'>

    fieldset {
        font-size:15px;
        padding:10px;
        width:220px;
    }

</style>



<head>
    <Title>Lista de Usuarios </Title>
</head>


<jsp:include page="/WEB-INF/view/marketing/marketing_header.jsp" />

<%

    String fname;
    if(request.getAttribute("fname")!= null){
        fname = (String)request.getAttribute("fname");
    }else {
        fname = "Soy nulo";
    }

    int id = -1;
    if(request.getAttribute("id")!= null){
        id = (Integer)request.getAttribute("id");
    }else {
        id = -2;
    }


%>


<body>
<h1> Lista de Usuarios: <%= fname %> </h1>
</body>



<%
    List<UsersDTO> usuarios = (List)request.getAttribute("usuarios");
    List<UsersDTO> usuariosAdded = (List)request.getAttribute("usuarioslista");
%>


<table border="1">

    <caption><h2> Usuarios Añadidos</h2><caption>

        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Genero</th>
            <th>Email</th>
            <th>Numero</th>
            <th>Ciudad</th>
            <th>Region</th>
            <th></th>
        </tr>
            <%
                    if (usuariosAdded != null && !usuariosAdded.isEmpty())
                    for (UsersDTO user : usuariosAdded) {
                %>

        <tr>
            <td><%= user.getName()%></td>
            <td><%= user.getSurname()%></td>
            <td><%= user.getGender()%></td>
            <td><%= user.getEmail()%></td>
            <td><%= user.getNumber()%></td>
            <td><%= user.getCity()%></td>
            <td><%= user.getRegion()%></td>
            <td><a href="/marketing/crearLista/<%= user.getUserID()%>/<%=id%>/deleteUser">Borrar</a></td>
        </tr>

            <%
                    }
                %>

</table>



<form action="/marketing/crearLista/filtrar" method="POST">
    <table>
        <caption><h2> Buscar Usuarios </h2><caption>
            <tr>
                <td>
                    <label for="NombreUsuario">Nombre usuario</label>
                    <input type="NombreUsuario" id="NombreUsuario" name="NombreUsuario" size="50">
                </td>
            </tr>

            <tr>
                <td>
                    Ordernar por: <select name="OrderBy">
                    <option value="">- - -</option>
                    <option value="username">Nombre</option>
                    <option value="email">Email</option>
                    <option value="postalCode">Codigo Postal</option>
                </select>
                </td>
            </tr>

            <tr><td> <input type="submit" value="Filtrar"></td></tr>

    </table>
</form>


<table border="1">

    <caption><h2>Usuarios</h2><caption>

        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Genero</th>
            <th>Email</th>
            <th>Numero</th>
            <th>Ciudad</th>
            <th>Region</th>
            <th>Codigo Postal</th>
            <th></th>
        </tr>
            <%
                    if (usuarios != null && !usuarios.isEmpty())
                    for (UsersDTO user : usuarios) {
                %>

        <tr>
            <td><%= user.getName()%></td>
            <td><%= user.getSurname()%></td>
            <td><%= user.getGender()%></td>
            <td><%= user.getEmail()%></td>
            <td><%= user.getNumber()%></td>
            <td><%= user.getCity()%></td>
            <td><%= user.getRegion()%></td>
            <td><%= user.getPostalCode()%></td>
            <td><a href="/marketing/crearLista/<%= user.getUserID()%>/<%=id%>/addUser">Añadir</a></td>
        </tr>

            <%
                    }
            %>

</table>


<br></br>
<form action="MarketingBorrarListaServlet?idList=<%=id%>" method="POST">
    <table>
        <tr><td> <input type="submit" value="BorrarLista"></td></tr>
    </table>
</form>



</html>