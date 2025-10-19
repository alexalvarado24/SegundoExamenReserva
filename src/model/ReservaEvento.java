/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import enums.TipoAula;
import enums.TipoEvento;

/**
 *
 * @author Emir Alvarado
 */
public class ReservaEvento extends Reserva {
    private TipoEvento tipoEvento;

    public ReservaEvento(int id, Aula aula, java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin, String responsable, TipoEvento tipoEvento) {
        super(id, aula, fecha, horaInicio, horaFin, responsable);
        this.tipoEvento = tipoEvento;
    }

    public TipoEvento getTipoEvento() { return tipoEvento; }

    @Override
    public boolean validar() {
        if(aula.getTipo() != TipoAula.AUDITORIO) {
            System.out.println("Error: Los eventos solo pueden reservar auditorios.");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", tipoEvento=" + tipoEvento;
    }
}