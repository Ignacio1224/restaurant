<%-- 
    Document   : mozo
    Created on : Oct 29, 2019, 10:09:59 PM
    Author     : Ignacio Cabrera
--%>

<%@page import="logica.Mozo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Mozo usuario = (Mozo) request.getSession().getAttribute("Usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio (Mozo)</title>
    </head>
    <body>
        <h1>Bienvenido <%=usuario.getNombreCompleto() %></h1>
    </body>
</html>
