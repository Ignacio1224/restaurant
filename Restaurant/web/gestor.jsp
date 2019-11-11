<%-- 
    Document   : mozo
    Created on : Oct 29, 2019, 10:09:59 PM
    Author     : Ignacio Cabrera
--%>

<%@page import="logica.Gestor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object usuarioEnSesion = request.getSession().getAttribute("Usuario");
    if (usuarioEnSesion == null) {
        response.sendRedirect("gestorLogin.jsp?message=Debes estar logueado!");
        return;
    }
    Gestor usuario = (Gestor) usuarioEnSesion;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio (Gestor)</title>
    </head>
    <body>
        <h1>Bienvenido <%=usuario.getNombreCompleto() %></h1>
    </body>
</html>
