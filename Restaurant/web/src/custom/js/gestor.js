let numeroMesaG;

$(document).ready(() => {
    const eventosSSE = new EventSource("gestor?accion=conectarSSE");

    eventosSSE.onerror = function (evento) {
        eventosSSE.close();
    };

    eventosSSE.addEventListener("eventoMostrarNombreUsuario", function ( {data}) {
        $("#spanNombreUsuario").html(data);
    }, false);
    
    
    eventosSSE.addEventListener("eventoNombreUnidadProcesadora", function ( {data}) {
        $("#spanNombreUnidadProcesadora").append(data);
    }, false);


});
