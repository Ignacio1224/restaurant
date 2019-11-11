$(document).ready(() => {
    const eventosSSE = new EventSource("mozo?accion=conectarSSE");

    eventosSSE.onerror = function (evento) {
        eventosSSE.close();
    };

    eventosSSE.addEventListener("eventoNombreUsuario", function ( {data}) {
        $("#spanNombreUsuario").html(data);
    }, false);

    eventosSSE.addEventListener("eventoCargarMesas", function ( {data}) {
        $("#divMesas").html(data);
    }, false);

    eventosSSE.addEventListener("eventoNotificarError", function ( {data}) {
        const divError = `<div class="alert alert-danger" role="alert">${data}</div>`;
        const footer = `<button type="button" class="btn btn-danger" data-dismiss="modal">Entendido</button>`;
        cargarModal("Ooooops!", divError, footer);
    }, false);

});

function abrirMesa(numero) {
    $.get(`mozo?accion=abrirMesa&mesa=${numero}`);
    $("#modalMesa").modal("hide");
}

function cerrarMesa(numero) {
    $.get(`mozo?accion=cerrarMesa&mesa=${numero}`);
    $("#modalMesa").modal("hide");
}

function cargarModalMesa(numeroMesa, estado) {
    $("#modalMesaLongTitle").html(`Mesa Nº ${numeroMesa}`);
    if (!!!estado) {
        $("#modalMesa .modal-body").html("<p>Abre la mesa para agregar servicios</p>");
        $("#modalMesa .modal-footer").html(`
            <button type="button" class="btn btn-success" onclick="abrirMesa(${numeroMesa})">Abrir mesa</button>
        `);
    } else {
        $("#modalMesa .modal-footer").html(`
            <button type="button" class="btn btn-success" onclick="cerrarMesa(${numeroMesa})">Cerrar mesa</button>
        `);
    }

    $("#modalMesa").modal("show");
}

function cargarModal(titulo, body, footer) {
    $("#modalLongTitle").html(titulo);

    $("#modal .modal-body").html(body);
    $("#modal .modal-footer").html(footer);

    $("#modal").modal("show");
}