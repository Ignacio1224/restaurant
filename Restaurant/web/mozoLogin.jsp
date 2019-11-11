<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = request.getParameter("message");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./src/bootstrap-4.3.1/css/bootstrap.min.css"/>
        <script src="./src/jquery3.4.1.js"></script>
        <script src="./src/popper1.16.0.js"></script>
        <script src="./src/bootstrap-4.3.1/js/bootstrap.min.js"></script>
        <title>Login (Mozo)</title>
    </head>
    <body class="container h-100">

        <div class="row">
            <div class="card mt-5 mb-5 col-8 offset-2">
                <div class="card-body">
                    <h4 class="text-center mt-3 mb-4">Inicio de sesión (Mozo)</h4>

                    <form method="POST" action="login" class="mt-5">

                        <% if (message != null) {%>
                        <div class="alert alert-danger">
                            <%=message%>
                        </div>
                        <% }%>

                        <div class="form-group">
                            <input type="text" class="form-control" id="inputUsername" name="inputUsername" aria-describedby="userNameHelp" placeholder="Ingrese el nombre de usuario" />
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Contraseña" />
                        </div>

                        <input type="text" value="loginMozo" id="inputAccion" name="inputAccion" hidden="hidden" />

                        <button type="submit" class="btn btn-primary mb-4">Entrar</button>
                    </form>

                </div>
            </div>
        </div>

        <style>
            body {
                background: rgba(4,11,  115,1);
                background: -moz-radial-gradient(center, ellipse cover, rgba(4,11,115,1) 0%, rgba(4,11,115,1) 23%, rgba(36,12,245,1) 100%);
                background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%, rgba(4,11,115,1)), color-stop(23%, rgba(4,11,115,1)), color-stop(100%, rgba(36,12,245,1)));
                background: -webkit-radial-gradient(center, ellipse cover, rgba(4,11,115,1) 0%, rgba(4,11,115,1) 23%, rgba(36,12,245,1) 100%);
                background: -o-radial-gradient(center, ellipse cover, rgba(4,11,115,1) 0%, rgba(4,11,115,1) 23%, rgba(36,12,245,1) 100%);
                background: -ms-radial-gradient(center, ellipse cover, rgba(4,11,115,1) 0%, rgba(4,11,115,1) 23%, rgba(36,12,245,1) 100%);
                background: radial-gradient(ellipse at center, rgba(4,11,115,1) 0%, rgba(4,11,115,1) 23%, rgba(36,12,245,1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#040b73', endColorstr='#240cf5', GradientType=1 );
            }
        </style>

    </body>
</html>
