/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import logica.Gestor;


public class ControladorUProcesadora {
    
    private VistaUProcesadora vista;
    private Gestor usuario;

    public ControladorUProcesadora(VistaUProcesadora vista, Gestor usuario) {
        this.vista = vista;
        this.usuario = usuario;
    }

    public void vistaLista() {
        vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        vista.mostrarUProcesadoras();
    }
}
