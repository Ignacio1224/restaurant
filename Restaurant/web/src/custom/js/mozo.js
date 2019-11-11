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
        $("#modalMesa .modal-body").html(`
            <p>Añadir al servicio:</p>
            <form method="POST" action="mozo">
                <div class="form-group">
                    <label for="select-products" class="col-form-label">Producto: *</label>
                    <select class="custom-select" id="select-products" required>
                        <option value="" selected>------</option>
                    </select>
                </div>
                <div class="form-group">
                  <label for="txt-cantidad" class="col-form-label">Cantidad: *</label>
                  <input type="number" min=0 class="form-control" id="txt-cantidad" required></input>
                </div>
                <div class="form-group">
                  <label for="message-text" class="col-form-label">Descripción:</label>
                  <textarea class="form-control" id="message-text"></textarea>
                </div>
                <input type="text" value="aniadirProductoAServicio" id="accion" name="accion" hidden="hidden" />
                <input type="text" value="${numeroMesa}" id="mesa" name="mesa" hidden="hidden" />
                <button type="submit" class="btn btn-success">Añadir</button>
            <form>
        `);
        
        $("#modalMesa .modal-footer").html(`
            <button type="button" class="btn btn-warning" onclick="cerrarMesa(${numeroMesa})">Cerrar mesa</button>
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