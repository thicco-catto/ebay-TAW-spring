<%--
    Document   : homePage
    Created on : 25-abr-2022, 20:15:25
    Author     : mjura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ebay</title>

    <style>
        * {
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;

        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
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

<%
    String msjError = (String)request.getAttribute("error");
    if (msjError == null) msjError="";
%>

<body>

<div class="topnav">
    <li style="float:right"><a class="active" href="">Home</a></li>
    <li style="float:right"><a class="active" href="homeRegisterServlet">Registrate como vendedor</a></li>
</div>

<div class="content">
    <h1>Bienvenidx a ebay</h1>
    <%= msjError%>
    <form action="/autentica" method="POST">
        <table border="0">
            <tbody>
            <tr>
                <td>Email:</td>
                <td><input required type="text" name="email" value="" /></td>
            </tr>
            <tr>
                <td>Contraseña:</td>
                <td><input required type="password" name="password" value="" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Iniciar" /></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div class="footer">
    <p>© 2022 EbayTAW, Inc</p>
</div>



</body>
</html>
