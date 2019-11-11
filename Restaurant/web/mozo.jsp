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
        <link rel="stylesheet" type="text/css" href="./src/bootstrap-4.3.1/css/bootstrap.min.css"/>
        <script src="./src/bootstrap-4.3.1/js/bootstrap.min.js"></script>

        <!--
        <script src="./src/jquery3.4.1.js"></script>
        <script src="./src/popper1.16.0.js"></script> 
        -->

        <style>
            figure{
                background-color:whitesmoke;
            }

            body{
                background-color: #dadada
            }

            .btn-mesa{
                background-color: #5ed14f;
                padding: 0 11px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio (Mozo)</title>
    </head>
    <body>
        <div class="container">
            <div class="row mt-3 mb-3">
                <div class="col-lg">
                    <h3>Bienvenido: <em><%=usuario.getNombreCompleto()%></em></h3>
                </div>
                <div class="col-lg-2">
                    <form method="POST" action="login">
                        <input type="text" value="logoutMozo" id="inputAccion" name="inputAccion" hidden="hidden" />
                        <button type="submit" class="btn-primary p-2 pl-3 pr-3"><em>Log out<em></button>
                    </form>
                </div>
            </div>
            <!--Row 1-->
            <div class="row">
                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m1">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 1</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m2">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 2</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m3">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>    
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 3</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m4">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 4</b></p>
                        </figcaption>
                    </figure>
                </div>
            </div>

            <div class="w-100"></div> <!-- Per las duda -->

            <!--Row 2-->
            <div class="row mt-3 mb-5">
                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m5">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 5</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m6">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 6</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m7">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 7</b></p>
                        </figcaption>
                    </figure>
                </div>

                <div class="col-lg-3">
                    <figure class="figure border rounded-right p-3">
                        <button class="btn-mesa" id="m8">
                            <img src="img/mesa_Icon.png" class="figure-img img-fluid rounded" alt="Icono mesa."/>
                        </button>
                        <figcaption class="figure-caption text-right">
                            <p class="mt-2"><b>Mesa 8</b></p>
                        </figcaption>
                    </figure>
                </div>

            </div>
        </div>
    </body>
</html>
