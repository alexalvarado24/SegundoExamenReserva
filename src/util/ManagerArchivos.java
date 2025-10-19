/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import model.Aula;
import model.Reserva;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author Emir Alvarado
 */
public class ManagerArchivos {
  public static void guardarAulas(ArrayList<Aula> aulas, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Aula a : aulas) {
                writer.write(a.getId() + "," + a.getNombre() + "," + a.getTipo() + "," + a.getCapacidad());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void guardarReservas(ArrayList<Reserva> reservas, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Reserva r : reservas) {
                writer.write(r.toString());
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}   

