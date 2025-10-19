/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import model.Reserva;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 *
 * @author Emir Alvarado
 */
public class ReservaUtils { 
    //Comprueba si una nueva reserva se superpone con alguna reserva ya existente en la misma aula y fecha.
    public static boolean hayConflicto(Reserva nueva, ArrayList<Reserva> reservas) {
        return reservas.stream()
                .filter(r -> r.getAula().equals(nueva.getAula())) //Filtra todas las reservas para que sean del mismo aula
                .filter(r -> r.getFecha().equals(nueva.getFecha()))  //Filtra para que sean de la misma fecha
                .anyMatch(r -> (nueva.getHoraInicio().isBefore(r.getHoraFin()) &&
                                nueva.getHoraFin().isAfter(r.getHoraInicio()) &&
                                r.getEstado().equals("ACTIVA"))); //Filtra si estan activas
    }
//Realiza una busqueda a traves del nombre del responsable
    public static ArrayList<Reserva> buscarPorResponsable(String nombre, ArrayList<Reserva> reservas) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        reservas.stream()
                .filter(r -> r.getResponsable().toLowerCase().contains(nombre.toLowerCase()))
                .forEach(resultado::add);
        return resultado;
    }
}