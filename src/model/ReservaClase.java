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
//Hereda de la clase Reserva
public class ReservaClase extends Reserva {
    //Super clase con atributos
    public ReservaClase(int id, Aula aula, java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin, String responsable) {
        super(id, aula, fecha, horaInicio, horaFin, responsable);
    }
//Valida que el tipo de reserva coincida con el tipo de aula
    //Las reserva Clase solo estan disponibles en TEORICA
    @Override
    public boolean validar() {
        if(aula.getTipo() != TipoAula.TEORICA) {
            System.out.println("Error: Las clases solo pueden reservar aulas TEORICAS.");
            return false;
        }
        return true;
    }
}