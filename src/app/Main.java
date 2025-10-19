/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.*;
import enums.*;
import java.io.File;
import util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emir Alvarado
 */
public class Main {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Aula> aulas = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();
    static int aulaId = 1;
    static int reservaId = 1;

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- GESTOR DE RESERVAS ITCA ---");
            System.out.println("1. Registrar Aula");
            System.out.println("2. Listar Aulas");
            System.out.println("3. Registrar Reserva");
            System.out.println("4. Listar Reservas");
            System.out.println("5. Cancelar Reserva");
            System.out.println("6. Buscar reservas por responsable");
            System.out.println("7. Generar reportes");
            System.out.println("0. Salir");
            System.out.print("Ingrese su respuesta: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    registrarAula();
                    break;
                case 2:
                    listarAulas();
                    break;
                case 3:
                    registrarReserva();
                    break;
                case 4:
                    listarReservas();
                    break;
                case 5:
                    cancelarReserva();
                    break;
                case 6:
                    buscarPorResponsable();
                    break;
                case 7:
                    generarReportes();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 0);
    }



    static void registrarAula() {
    System.out.print("Nombre del aula: ");
    String nombre = entrada.nextLine();

    // Mostrar opciones de tipo de aula
    System.out.println("Seleccione el tipo de aula:");
    TipoAula[] tipos = TipoAula.values();
    for (int i = 0; i < tipos.length; i++) {
        System.out.println((i + 1) + ". " + tipos[i]);
    }
    int opcionTipo = entrada.nextInt();
    entrada.nextLine();

    // Validar opción
    if (opcionTipo < 1 || opcionTipo > tipos.length) {
        System.out.println("Opción de tipo no válida.");
        return;
    }

    TipoAula tipo = tipos[opcionTipo - 1];

    System.out.print("Capacidad: ");
    int capacidad = entrada.nextInt();
    entrada.nextLine();

    aulas.add(new Aula(aulaId++, nombre, tipo, capacidad));
    System.out.println("Aula registrada correctamente.");
}



    static void listarAulas() {
        if (aulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
        } else {
            aulas.forEach(System.out::println);
        }
    }

    static void registrarReserva() {
    System.out.print("ID del aula: ");
    int id = entrada.nextInt();
    entrada.nextLine();

    Aula aula = aulas.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    if (aula == null) {
        System.out.println("Aula no encontrada.");
        return;
    }

    System.out.print("Fecha (YYYY-MM-DD): ");
    LocalDate fecha = LocalDate.parse(entrada.nextLine());
    System.out.print("Hora inicio (HH:MM): ");
    LocalTime hi = LocalTime.parse(entrada.nextLine());
    System.out.print("Hora fin (HH:MM): ");
    LocalTime hf = LocalTime.parse(entrada.nextLine());
    System.out.print("Responsable: ");
    String resp = entrada.nextLine();

    // Selección del tipo de reserva
    System.out.println("Seleccione el tipo de reserva:");
    System.out.println("1. CLASE");
    System.out.println("2. PRACTICA");
    System.out.println("3. EVENTO");
    int opcionReserva = entrada.nextInt();
    entrada.nextLine();

    Reserva r = null;
    try {
        switch (opcionReserva) {
            case 1:
                r = new ReservaClase(reservaId, aula, fecha, hi, hf, resp);
                break;
            case 2:
                r = new ReservaPractica(reservaId, aula, fecha, hi, hf, resp);
                break;
            case 3:
                // Selección del tipo de evento
                System.out.println("Seleccione tipo de evento:");
                TipoEvento[] eventos = TipoEvento.values();
                for (int i = 0; i < eventos.length; i++) {
                    System.out.println((i + 1) + ". " + eventos[i]);
                }
                int opcionEvento = entrada.nextInt();
                entrada.nextLine();

                if (opcionEvento < 1 || opcionEvento > eventos.length) {
                    System.out.println("Opción de evento no válida.");
                    return;
                }

                TipoEvento te = eventos[opcionEvento - 1];
                r = new ReservaEvento(reservaId, aula, fecha, hi, hf, resp, te);
                break;
            default:
                System.out.println("Tipo de reserva no válido.");
                return;
        }

        // Validación de conflicto y registro
        if (r.validar()) {
            if (ReservaUtils.hayConflicto(r, reservas)) {
                System.out.println("Error: Conflicto de horario con otra reserva activa.");
                return;
            }
            reservas.add(r);
            reservaId++;
            System.out.println("Reserva registrada correctamente.");
        }
    } catch (Exception e) {
        System.out.println("Error al registrar reserva: " + e.getMessage());
    }
}


    static void listarReservas() {
        reservas.forEach(System.out::println);
    }

    static void cancelarReserva() {
        System.out.print("ID de la reserva a cancelar: ");
        int id = entrada.nextInt();
        entrada.nextLine();
        Reserva r = reservas.stream().filter(res -> res.getId() == id && res.getEstado().equals("ACTIVA")).findFirst().orElse(null);
        if (r == null) {
            System.out.println("Reserva no encontrada o ya cancelada.");
            return;
        }
        r.cancelar();
        System.out.println("Reserva cancelada.");
    }

    static void buscarPorResponsable() {
        System.out.print("Nombre del responsable: ");
        String nombre = entrada.nextLine();
        ArrayList<Reserva> resultado = ReservaUtils.buscarPorResponsable(nombre, reservas);
        if (resultado.isEmpty()) {
            System.out.println("No se encontraron reservas.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    static void generarReportes() {

                File carpeta = new File("data");
                if (!carpeta.exists()) {
                    carpeta.mkdir();
                }
                String rutaCSV = "data/ReporteReservas.csv";
                GenerarReportes.exportarReporteCompletoCSV(reservas, rutaCSV);
               
    }


}
