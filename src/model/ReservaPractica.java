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
public class ReservaPractica extends Reserva {
    public ReservaPractica(int id, Aula aula, java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin, String responsable) {
        super(id, aula, fecha, horaInicio, horaFin, responsable);
    }
//Las reservas Practica solo estan disponibles en LABORATORIO
    @Override
    public boolean validar() {
        if(aula.getTipo() != TipoAula.LABORATORIO) {
            System.out.println("Error: Las prácticas solo pueden reservar laboratorios.");
            return false;
        }
        return true;
    }
}
