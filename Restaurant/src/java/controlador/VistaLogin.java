/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import logica.Gestor;
import logica.Mozo;

/**
 *
 * @author alumnoFI
 */
public interface VistaLogin {

    public void accesoDenegadoMozo(String message);

    public void accesoPermitidoMozo(Mozo usuario);

    public void accesoDenegadoGestor(String message);

    public void accesoPermitidoGestor(Gestor usuario);

}
