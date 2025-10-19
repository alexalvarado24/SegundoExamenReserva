/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Reserva;
import model.ReservaEvento;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Emir Alvarado
 */
public class GenerarReportes {

    public static void exportarReporteCompletoCSV(List<Reserva> reservas, String nombreArchivo) {
        if (reservas.isEmpty()) { // Verifica si hay reservas; si no hay, no hace nada
            System.out.println("No hay reservas para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(nombreArchivo), "UTF-8"))) {

            // BOM UTF-8 para que Excel reconozca correctamente los acentos
            writer.write("\uFEFF");

            // ---- Sección 1: Detalle de reservas ----
            writer.write("ID;Aula;Fecha;HoraInicio;HoraFin;Responsable;Estado;TipoReserva;TipoEvento\n");
            for (Reserva r : reservas) {
                String tipoEvento = "";
                if (r instanceof ReservaEvento) { // Si la reserva es un evento, obtiene su tipo de evento
                    tipoEvento = ((ReservaEvento) r).getTipoEvento().toString();
                }
                // Construye la línea del CSV
                String linea = r.getId() + ";"
                        + "\"" + r.getAula().getNombre() + "\";"
                        + r.getFecha() + ";"
                        + r.getHoraInicio() + ";"
                        + r.getHoraFin() + ";"
                        + "\"" + r.getResponsable() + "\";"
                        + r.getEstado() + ";"
                        + r.getClass().getSimpleName() + ";"
                        + tipoEvento;
                writer.write(linea + "\n");
            }

            writer.write("\n"); // línea vacía

            // ---- Sección 2: Top 3 aulas con más horas ----
            writer.write("----Top 3 aulas con más horas----\n");
            writer.write("Aula;Horas\n");
            // Calcula la duración de cada reserva en horas y acumula por aula
            Map<String, Integer> horasPorAula = new HashMap<>();
            for (Reserva r : reservas) {
                int duracion = r.getHoraFin().getHour() - r.getHoraInicio().getHour();
                horasPorAula.put(r.getAula().getNombre(),
                        horasPorAula.getOrDefault(r.getAula().getNombre(), 0) + duracion);
            }
            // Ordena por horas descendentes y toma las 3 primeras
            horasPorAula.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(3)
                    .forEach(e -> {
                        try {
                            writer.write("\"" + e.getKey() + "\";" + e.getValue() + "\n");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

            writer.write("\n");

            // ---- Sección 3: Ocupación por tipo de aula ----
            writer.write("----Ocupación por tipo de aula----\n");
            writer.write("Tipo;CantidadReservas\n");
            // Cuenta cuántas reservas hay por tipo de aula
            Map<String, Long> ocupacion = reservas.stream()
                    .collect(Collectors.groupingBy(r -> r.getAula().getTipo().toString(), Collectors.counting()));
            for (Map.Entry<String, Long> e : ocupacion.entrySet()) {
                writer.write(e.getKey() + ";" + e.getValue() + "\n");
            }

            writer.write("\n");

            // ---- Sección 4: Distribución por tipo de reserva ----
            writer.write("----Distribución por tipo de reserva----\n");
            writer.write("TipoReserva;Cantidad\n");
            // Cuenta cuántas reservas hay por clase (ReservaClase, ReservaPractica, ReservaEvento)
            Map<String, Long> distribucion = reservas.stream()
                    .map(r -> r.getClass().getSimpleName())
                    //Collector de la API de Streams en Java.
                    //Permite agrupar elementos de un stream en un Map según hash
                    .collect(Collectors.groupingBy(r -> r, Collectors.counting()));
            for (Map.Entry<String, Long> e : distribucion.entrySet()) {
                writer.write(e.getKey() + ";" + e.getValue() + "\n");
            }

            System.out.println("Archivo '" + nombreArchivo + "' exportado correctamente. Revise la carpeta del proyecto.");

        } catch (IOException e) {
            System.out.println("Error al exportar el archivo CSV: " + e.getMessage());
        }
    }
}
