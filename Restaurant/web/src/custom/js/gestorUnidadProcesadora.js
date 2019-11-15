let numeroMesaG;

$(document).ready(() => {
    const eventosSSE = new EventSource("seleccionarUnidadProcesadora?accion=conectarSSE");

    eventosSSE.onerror = function (evento) {
        eventosSSE.close();
    };

    eventosSSE.addEventListener("eventoCargarUnidadesProcesadoras", function ( {data}) {
        $("#selectUnidadProcesadora").html(data);
    }, false);

});
