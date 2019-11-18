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
<head>
    <link rel="stylesheet" type="text/css" href="./src/bootstrap-4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="./src/custom/css/mozo.css"/>
    <script src="./src/jquery3.4.1.js"></script>
    <script src="./src/popper1.16.0.js"></script> 
    <script src="./src/bootstrap-4.3.1/js/bootstrap.min.js"></script>
    <script src="./src/custom/js/gestor.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inicio (Gestor)</title>
</head>
<body>

    <div class="row bg-dark mb-4 pt-2 pb-2 text-light">
        <h3 class="col-6 offset-1">
            <span class="font-italic ml-4" id="spanNombreUnidadProcesadora">Unidad: </span> /
            Usuario: <span class="font-italic" id="spanNombreUsuario"></span>
        </h3>

        <form method="POST" action="login" class="col-1 offset-4">
            <input type="text" value="logoutGestor" id="inputAccion" name="inputAccion" hidden="hidden" />
            <button type="submit" class="btn btn-primary font-italic">Salir</button>
        </form>
    </div>

    <div class="container-fluid row">
        <!-- Panels -->
        <div class="col-12 row mt-5">

            <div class="card col-6">
                <div class="card-heading">
                    <h3>Pedidos pendientes</h3>
                </div>

                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Producto</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Descripción</th>
                                <th scope="col">Mesa</th>
                                <th scope="col">Mozo</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody id="pedidosPendientes">

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="card col-6">
                <div class="card-heading"><h3>Pedidos tomados</h3></div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Producto</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Descripción</th>
                                <th scope="col">Mesa</th>
                                <th scope="col">Mozo</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody id="pedidosTomados">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
