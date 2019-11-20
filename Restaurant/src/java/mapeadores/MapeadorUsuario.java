/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeadores;

import java.sql.ResultSet;
import java.util.ArrayList;
import logica.Usuario;
import persistencia.Mapeador;
import utilidades.CustomException;

/**
 *
 * @author Dell
 */
public class MapeadorUsuario implements Mapeador {

    private Usuario u;

    public MapeadorUsuario() {
    }

    public MapeadorUsuario(Usuario u) {
        this.u = u;
    }

    public void setUsuario(Usuario u) {
        this.u = u;
    }

    @Override
    public int getOid() {
        return u.getOid();
    }

    @Override
    public void setOid(int oid) {
        u.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public ArrayList<String> getSqlActualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> getSqlBorrar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSqlSeleccionar() {
        return "select * from USUARIO";
    }

    @Override
    public void crearNuevo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void leerCompuesto(ResultSet rs) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getObjeto() {
        return u;
    }

    @Override
    public void leerComponente(ResultSet rs) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
