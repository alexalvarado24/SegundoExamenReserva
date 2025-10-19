/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import enums.TipoAula;

/**
 *
 * @author Emir Alvarado
 */
public class ReservaClase extends Reserva {
    public ReservaClase(int id, Aula aula, java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin, String responsable) {
        super(id, aula, fecha, horaInicio, horaFin, responsable);
    }

    @Override
    public boolean validar() {
        if(aula.getTipo() != TipoAula.TEORICA) {
            System.out.println("Error: Las clases solo pueden reservar aulas TEORICAS.");
            return false;
        }
        return true;
    }
}