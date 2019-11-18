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

    eventosSSE.addEventListener("eventoCargarPedidosPendientes", function ( {data}) {
        $("#pedidosPendientes").html(data);
    }, false);
    
    eventosSSE.addEventListener("eventoCargarPedidosTomados", function ( {data}) {
        $("#pedidosTomados").html(data);
    }, false);


});

function tomarPedido(indexItem) {
    $.get(`gestor?accion=tomarPedido&indexItem=${indexItem}`);
}

function finalizarPedido(indexItem) {
    $.get(`gestor?accion=finalizarPedido&indexItem=${indexItem}`);
}