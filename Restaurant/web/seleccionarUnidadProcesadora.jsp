<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = request.getParameter("message");
%>

<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./src/bootstrap-4.3.1/css/bootstrap.min.css"/>
        <script src="./src/jquery3.4.1.js"></script>
        <script src="./src/popper1.16.0.js"></script>
        <script src="./src/bootstrap-4.3.1/js/bootstrap.min.js"></script>
        <script src="./src/custom/js/gestorUnidadProcesadora.js"></script>

        <title>Selecci&oacute;n de Unidad Procesadora (Gestor)</title>
    </head>
    <body class="container h-100">

        <div class="row">
            <div class="card mt-5 mb-5 col-8 offset-2">
                <div class="card-body">
                    <h4 class="text-center mt-3 mb-4">Selecci&oacute;n de Unidad Procesadora</h4>

                    <form method="POST" action="seleccionarUnidadProcesadora" class="mt-5">

                        <% if (message != null) {%>
                        <div class="alert alert-danger">
                            <%=message%>
                        </div>
                        <% }%>

                        <div class="form-group">
                            <label for="selectUnidadProcesadora">Unidad procesadora</label>
                            <input type="text" value="seleccionarUnidadProcesadora" name="accion" hidden="hidden" />
                            <select class="form-control" id="selectUnidadProcesadora" name="unidadProcesadora">

                            </select>
                        </div>


                        <button type="submit" class="btn btn-primary mb-4">Entrar</button>
                    </form>

                </div>
            </div>
        </div>

        <style>
            body {
                background: rgba(145,109,0,1);
                background: -moz-radial-gradient(center, ellipse cover, rgba(145,109,0,1) 0%, rgba(145,109,0,1) 11%, rgba(255,193,7,1) 83%, rgba(255,193,7,1) 100%);
                background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%, rgba(145,109,0,1)), color-stop(11%, rgba(145,109,0,1)), color-stop(83%, rgba(255,193,7,1)), color-stop(100%, rgba(255,193,7,1)));
                background: -webkit-radial-gradient(center, ellipse cover, rgba(145,109,0,1) 0%, rgba(145,109,0,1) 11%, rgba(255,193,7,1) 83%, rgba(255,193,7,1) 100%);
                background: -o-radial-gradient(center, ellipse cover, rgba(145,109,0,1) 0%, rgba(145,109,0,1) 11%, rgba(255,193,7,1) 83%, rgba(255,193,7,1) 100%);
                background: -ms-radial-gradient(center, ellipse cover, rgba(145,109,0,1) 0%, rgba(145,109,0,1) 11%, rgba(255,193,7,1) 83%, rgba(255,193,7,1) 100%);
                background: radial-gradient(ellipse at center, rgba(145,109,0,1) 0%, rgba(145,109,0,1) 11%, rgba(255,193,7,1) 83%, rgba(255,193,7,1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#916d00', endColorstr='#ffc107', GradientType=1 );
            }
        </style>

    </body>
</html>