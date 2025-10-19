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
    public static boolean hayConflicto(Reserva nueva, ArrayList<Reserva> reservas) {
        return reservas.stream()
                .filter(r -> r.getAula().equals(nueva.getAula()))
                .filter(r -> r.getFecha().equals(nueva.getFecha()))
                .anyMatch(r -> (nueva.getHoraInicio().isBefore(r.getHoraFin()) &&
                                nueva.getHoraFin().isAfter(r.getHoraInicio()) &&
                                r.getEstado().equals("ACTIVA")));
    }

    public static ArrayList<Reserva> buscarPorResponsable(String nombre, ArrayList<Reserva> reservas) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        reservas.stream()
                .filter(r -> r.getResponsable().toLowerCase().contains(nombre.toLowerCase()))
                .forEach(resultado::add);
        return resultado;
    }
}