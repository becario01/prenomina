/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Becarios
 */
public class Rincidencia {
        private int IdNomIncidencia;
        private String incidencia;
        private int estatus;

    public Rincidencia(int IdNomIncidencia, String incidencia, int estatus) {
        this.IdNomIncidencia = IdNomIncidencia;
        this.incidencia = incidencia;
        this.estatus = estatus;
    }

    public int getIdNomIncidencia() {
        return IdNomIncidencia;
    }

    public void setIdNomIncidencia(int IdNomIncidencia) {
        this.IdNomIncidencia = IdNomIncidencia;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return  incidencia ;
    }

   


    
    
    
}
