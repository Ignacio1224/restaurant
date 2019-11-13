<%@page import="logica.Mesa"%>
<%@page import="logica.Mozo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion == null) {
        response.sendRedirect("mozoLogin.jsp?message=Debes estar logueado!");
        return;
    }

    Mozo usuario = (Mozo) sesion.getAttribute("Usuario");

    if (usuario == null) {
        response.sendRedirect("mozoLogin.jsp?message=Debes estar logueado!");
        return;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./src/bootstrap-4.3.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="./src/custom/css/mozo.css"/>
        <script src="./src/jquery3.4.1.js"></script>
        <script src="./src/popper1.16.0.js"></script> 
        <script src="./src/bootstrap-4.3.1/js/bootstrap.min.js"></script>
        <script src="./src/custom/js/mozo.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio (Mozo)</title>
    </head>
    <body>

        <div class="row bg-dark mb-4 pt-2 pb-2 text-light">
            <h3 class="col-6 offset-1">Bienvenido: <span class="font-italic" id="spanNombreUsuario"></span></h3>

            <form method="POST" action="login" class="col-1 offset-4">
                <input type="text" value="logoutMozo" id="inputAccion" name="inputAccion" hidden="hidden" />
                <button type="submit" class="btn btn-primary font-italic">Salir</button>
            </form>
<!--            <div class="col-1 offset-4">
                <button type="button" class="btn btn-primary font-italic" onclick="salir()">Salir</button>
            </div>-->
        </div>

        <div class="container">
            <!-- Mesas -->
            <div class="row mt-5" id="divMesas">

            </div>

        </div>

        <!-- Modal -->
        <div class="modal fade" id="modalMesa" tabindex="-1" role="dialog" aria-labelledby="modalMesaTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        
                        <h5 class="modal-title" id="modalMesaLongTitle"></h5>
                        
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLongTitle"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
