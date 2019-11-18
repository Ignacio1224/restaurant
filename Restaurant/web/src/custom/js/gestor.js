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

function mostrarError(message) {
    const divError = `<div class="alert alert-danger" role="alert">${message}</div>`;
    const footer = `<button type="button" class="btn btn-danger" data-dismiss="modal">Entendido</button>`;
    cargarModal("Ooooops!", divError, footer);
}

function cargarModal(titulo, body, footer, enableClose = true) {
    $("#modalLongTitle").html(titulo);

    $("#modal .modal-body").html(body);
    $("#modal .modal-footer").html(footer);

    if (!enableClose) {
        $("#btn-close-modal").attr("disabled", true);
    } else {
        $("#btn-close-modal").attr("disabled", false);
    }

    $('#modal').modal({backdrop: 'static', keyboard: false, show: true});
}