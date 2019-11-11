<%@page import="logica.Gestor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion == null) {
        response.sendRedirect("gestorLogin.jsp?message=Debes estar logueado!");
        return;
    }

    Gestor usuario = (Gestor) sesion.getAttribute("Usuario");

    if (usuario == null) {
        response.sendRedirect("gestorLogin.jsp?message=Debes estar logueado!");
        return;
    }
    
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
