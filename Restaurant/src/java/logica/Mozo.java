package logica;

import java.util.ArrayList;
import utilidades.CustomException;

/**
 *
 * @author Ignacio Cabrera
 */
public class Mozo extends Usuario {

    /* Atributos */
    private ArrayList<Mesa> mesasAsignadas;
    private ArrayList<Transferencia> transferenciasActivas; // Transferencias enviadas (Emisor)
    private ArrayList<Transferencia> transferenciasPendientes; // Transferencias por aceptar (Receptor)

    /* Constructores */
    public Mozo(String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
        mesasAsignadas = new ArrayList<>();
        transferenciasActivas = new ArrayList<>();
        transferenciasPendientes = new ArrayList<>();
    }

    public Mozo(ArrayList<Mesa> mesasAsignadas, String nombreUsuario, String contrasena, String nombreCompleto) throws CustomException {
        super(nombreUsuario, contrasena, nombreCompleto);
        this.mesasAsignadas = mesasAsignadas;
        transferenciasActivas = new ArrayList<>();
        transferenciasPendientes = new ArrayList<>();
    }

    public void iniciarTransferencia(Mesa mesa, Mozo mozo) throws CustomException {
        if (!mesasAsignadas.contains(mesa)) {
            throw new CustomException("Mesa no disponible!");
        }

        Transferencia transferencia = new Transferencia(this, mozo, mesa);
        transferenciasActivas.add(transferencia);
    }

    public void agregarTransferenciaPendiente(Transferencia transferencia) throws CustomException {
        if (transferenciasPendientes.contains(transferencia)) {
            throw new CustomException("Transferencia ya solicitada!");
        }
        transferenciasPendientes.add(transferencia);
        // TODO: Notificar this (Evento al observador de este mozo)
    }

    public void aceptarTransferencia(Transferencia transferencia, boolean aceptar) throws CustomException {
        if (!transferenciasPendientes.contains(transferencia)) {
            throw new CustomException("Transferencia no disponible!");
        }
        transferencia.terminar(aceptar);
    }

    public void eliminarTransferencia(Transferencia transferencia, boolean aceptada) {
        transferenciasActivas.remove(transferencia);
        if (aceptada) {
            mesasAsignadas.remove(transferencia.getMesa());
            // TODO: Notificar (transferencia aceptada)
        } else {
            // TODO: Notificar (transferencia rechazada)
        }
    }

    public void agregarMesaAsignada(Mesa mesa) throws CustomException {
        if (mesasAsignadas.contains(mesa)) {
            throw new CustomException("Mesa ya asignada!");
        }
        mesasAsignadas.add(mesa);
    }

    @Override
    public Mozo login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

    /* Getters & Setters */
    public ArrayList<Mesa> getMesasAsignadas() {
        return mesasAsignadas;
    }

    public ArrayList<Transferencia> getTransferenciasActivas() {
        return transferenciasActivas;
    }

    public ArrayList<Transferencia> getTransferenciasPendientes() {
        return transferenciasPendientes;
    }

}
