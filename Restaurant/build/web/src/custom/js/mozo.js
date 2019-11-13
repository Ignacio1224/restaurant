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
    const cliente = $('#txt-cliente').val();
    $.get(`mozo?accion=cerrarMesa&mesa=${numero}&cliente=${cliente}`);
    $("#modalMesa").modal("hide");
}

function enviarItem() {

    const producto = $('#select-products').val();
    const cantidad = $('#txt-cantidad').val();
    const descripcion = $('#descripcion-text').val();
    const mesa = $('#mesa').val();

    $.get(`mozo?accion=aniadirItemAServicio&codigoProducto=${producto}&cantidadProducto=${cantidad}&descripcionItem=${descripcion}&mesa=${mesa}`);

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
            <div class="row col-12">
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

    $("#modalMesa").modal("show");
}

function cargarModal(titulo, body, footer) {
    $("#modalLongTitle").html(titulo);

    $("#modal .modal-body").html(body);
    $("#modal .modal-footer").html(footer);

    $("#modal").modal("show");
}