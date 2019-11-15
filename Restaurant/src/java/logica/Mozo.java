package logica;

import java.util.ArrayList;
import utilidades.CustomException;

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

    /* Comportamientos */
    private void avisar(Eventos evento) {
        setChanged();
        notifyObservers(evento);
    }

    public enum Eventos {
        nuevaTransferencia, transferenciaRechazada, transferenciaAceptada
    };

    @Override
    public Mozo login(String nombreUsuario, String contrasena) {
        if (this.nombreusuario.equals(nombreUsuario) && this.contrasena.equals(contrasena)) {
            return this;
        }
        return null;
    }

    public void agregarMesaAsignada(Mesa mesa) throws CustomException {
        if (mesasAsignadas.contains(mesa)) {
            throw new CustomException("Mesa ya asignada!");
        }
        mesasAsignadas.add(mesa);
    }

    public Mesa getMesaByNumero(int numero) {
        for (Mesa m : mesasAsignadas) {
            if (m.getNumero() == numero) {
                return m;
            }
        }
        return null;
    }

    /*Transferencia methods*/
    // Solo para Receptor
    public void agregarTransferenciaPendiente(Transferencia transferencia) throws CustomException {
        if (transferenciasPendientes.contains(transferencia)) {
            throw new CustomException("Transferencia ya solicitada!");
        }
        transferenciasPendientes.add(transferencia);
        avisar(Eventos.nuevaTransferencia);
    }

    public void aceptarTransferencia(Transferencia transferencia, boolean aceptar) throws CustomException {
        if (!transferenciasPendientes.contains(transferencia)) {
            throw new CustomException("Transferencia no disponible!");
        }
        transferencia.terminar(aceptar);
    }

    // Solo para Emisor
    public void iniciarTransferencia(Mesa mesa, Mozo mozoDestino) throws CustomException {
        if (!mesasAsignadas.contains(mesa)) {
            throw new CustomException("Mesa no disponible!");
        }

        Transferencia transferencia = new Transferencia(this, mozoDestino, mesa);
        transferenciasActivas.add(transferencia);
        transferencia.notificarReceptor();
    }

    public void eliminarTransferencia(Transferencia transferencia, boolean aceptada) throws CustomException {
        transferenciasActivas.remove(transferencia);
        if (aceptada) {
            mesasAsignadas.remove(transferencia.getMesa());
            avisar(Eventos.transferenciaAceptada);
        } else {
            avisar(Eventos.transferenciaRechazada);
        }
    }

    public void eliminarTransferenciaPendiente(Transferencia t) throws CustomException {

        if (!transferenciasPendientes.contains(t)) {
            throw new CustomException("No posee la transferencia");
        }
        transferenciasPendientes.remove(t);
    }

    public ArrayList<Mesa> getMesasAbiertas() {
        ArrayList<Mesa> abiertas = new ArrayList<>();

        for (Mesa m : mesasAsignadas) {
            if (m.isAbierta()) {
                abiertas.add(m);
            }
        }

        return abiertas;
    }

    public Transferencia getTransferenciaPendienteByMesa(Mesa m) {

        for (Transferencia t : transferenciasPendientes) {
            if (t.getMesa().equals(m)) {
                return t;
            }
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
