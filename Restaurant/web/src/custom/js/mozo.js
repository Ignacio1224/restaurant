let numeroMesaG;

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

    eventosSSE.addEventListener("eventoCargarProductos", function ( {data}) {
        $("#select-products").append(data);
    }, false);

    eventosSSE.addEventListener("eventoNotificarError", function ( {data}) {
        mostrarError(data);
    }, false);

    eventosSSE.addEventListener("eventoNumeroMesa", function ( {data}) {
        numeroMesaG = parseInt(data);
    }, false);

    eventosSSE.addEventListener("eventoMostrarCuenta", function ( {data}) {
        const body = data;

        $('#modal .modal-body').html(body);
        $('#modal').modal({backdrop: 'static', keyboard: false, show: true});
    }, false);
    
    eventosSSE.addEventListener("eventoResultadoTransferencia", function ( {data}) {
        const title = 'Resultado de la transferencia';
        const body = data;
        const footer = '<button type="button" class="btn btn-warning" data-dismiss="modal">Entendido</button>';
        cargarModal(title, body, footer);
    }, false);

    eventosSSE.addEventListener("eventoMozosLogueados", function ( {data}) {
        $("#selectMozosLogueados").append(data);
        $('#modal').modal({backdrop: 'static', keyboard: false, show: true});
    }, false);

    eventosSSE.addEventListener("eventoRecepcionTransferencia", function ( {data}) {

        const titulo = `Tienes una transferencia pendiente...`;
        const body = data;
        const footer = `<button type="button" class="btn btn-success" onclick="confirmarTransferencia(${numeroMesaG}, true)">Confirmar</button>
                        <button type="button" class="btn btn-danger" onclick="confirmarTransferencia(${numeroMesaG}, false)">Rechazar</button>`;

        cargarModal(titulo, body, footer, false);
    }, false);

});


//function salir() {
//    $.ajax({url: `login?inputAccion=logoutMozo`}).fail((error) => {
//        mostrarError(error);
//    });
//}

function confirmarTransferencia(numeroMesa, confirmada) {
    $("#modal").modal("hide");
    
    $('#modal').on('hidden.bs.modal', function (e) {
        $.get(`mozo?accion=confirmarTransferencia&mesa=${numeroMesa}&confirmada=${confirmada}`);
        $(this).unbind(e);
    });
}

function abrirMesa(numero) {
    $.get(`mozo?accion=abrirMesa&mesa=${numero}`);
    $("#modalMesa").modal("hide");
}

function cerrarMesa(numero) {
    const cliente = $('#txt-cliente').val();
    const footer = `<button type="button" class="btn btn-warning" onclick=confirmarCierre(${numero})>Confirmar</button>`;

    $.get(`mozo?accion=cerrarMesa&mesa=${numero}&idCliente=${cliente}`);

    $("#modalMesa").modal("hide");
    $('#modalLongTitle').html(`Mesa Nº ${numero}`);
    $('#modal .modal-footer').html(footer);
}

function confirmarCierre(numero) {
    $.get(`mozo?accion=confirmarCierre&mesa=${numero}`);
    $("#modal").modal("hide");
}

function enviarItem() {

    const producto = $('#select-products').val();
    const cantidad = $('#txt-cantidad').val();
    const descripcion = $('#descripcion-text').val();
    const mesa = $('#mesa').val();

    $.get(`mozo?accion=aniadirItemAServicio&codigoProducto=${producto}&cantidadProducto=${cantidad}&descripcionItem=${descripcion}&mesa=${mesa}`);

    $("#modalMesa").modal("hide");
}

function iniciarTransferencia(numeroMesa) {
    $("#modalMesa").modal("hide");

    const titulo = `Transferir mesa Nº ${numeroMesa}`;
    const body = `
        <div class="form-group">
            <label for="selectMozosLogueados">Mozos logueados</label>
            <select class="form-control" id="selectMozosLogueados">
                <option value="" selected>------</option>
            </select>
        </div>`;

    const footer = `<button type="button" class="btn btn-success" onclick="transferir(${numeroMesa})">Transferir</button>`;

    $("#modalLongTitle").html(titulo);
    $("#modal .modal-body").html(body);
    $("#modal .modal-footer").html(footer);

    $.get("mozo?accion=getMozosLogueados");

}

function transferir(numeroMesa) {
    $("#modal").modal("hide");

    const mozo = $("#selectMozosLogueados").val();
    $('#modal').on('hidden.bs.modal', function (e) {
        $.get(`mozo?accion=transferirMesa&mesa=${numeroMesa}&mozo=${mozo}`);
        $(this).unbind(e);
    });
}

function cargarModalMesa(numeroMesa, estado) {
    $("#modalMesaLongTitle").html(`Mesa Nº ${numeroMesa}`);
    if (!!!estado) {
        $("#modalMesa .modal-body").html("<p>Abre la mesa para agregar servicios</p>");
        $("#modalMesa .modal-footer").html(`
            <button type="button" class="btn btn-success" onclick="abrirMesa(${numeroMesa})">Abrir mesa</button>
        `);
    } else {
        $("#modalMesa .modal-body").html(`
            <div>
                <p>Añadir al servicio:</p>
                <div class="form-group">
                    <label for="select-products" class="col-form-label">Producto: *</label>
                    <select class="custom-select" id="select-products">
                        <option value="" selected>------</option>
                    </select>
                </div>
                <div class="form-group">
                  <label for="txt-cantidad" class="col-form-label">Cantidad: *</label>
                  <input type="number" min=0 class="form-control" id="txt-cantidad"></input>
                </div>
                <div class="form-group">
                  <label for="message-text" class="col-form-label">Descripción:</label>
                  <textarea class="form-control" id="descripcion-text" ></textarea>
                </div>
                <input type="text" value="${numeroMesa}" id="mesa" hidden="hidden" />
                <button class="btn btn-success" onclick="enviarItem();">Añadir</button>
            </div>
        `);

        $("#modalMesa .modal-footer").html(`
            <div class="row">
                <div class="col-8">
                    <input type="text" class="form-control" id="txt-cliente" placeholder="Id cliente"></input>
                </div>
                <div class="col-1 offset-1">
                    <button type="button" class="btn btn-warning" onclick="cerrarMesa(${numeroMesa})">Cerrar mesa</button>
                </div>
            </div>
        `);

        $.get("mozo?accion=cargarProductos");

    }

    $("#modalMesa .modal-footer").append(`
            
            <button type="button" class="btn btn-primary" onclick="iniciarTransferencia(${numeroMesa})">Transferir mesa</button>
        `);

    $('#modalMesa').modal({backdrop: 'static', keyboard: false, show: true});
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