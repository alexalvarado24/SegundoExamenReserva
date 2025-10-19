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
import java.util.InputMismatchException;
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
            try {
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
                    case 1: registrarAula(); break;
                    case 2: listarAulas(); break;
                    case 3: registrarReserva(); break;
                    case 4: listarReservas(); break;
                    case 5: cancelarReserva(); break;
                    case 6: buscarPorResponsable(); break;
                    case 7: generarReportes(); break;
                    case 0: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opción no válida."); break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Ingrese un número válido.");
                entrada.nextLine(); // limpiar buffer
                opcion = -1;
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                opcion = -1;
            }
        } while (opcion != 0);
    }

    // Registrar un aula con validación en bucles
    static void registrarAula() {
        String nombre;
        while (true) {
            System.out.print("Nombre del aula: ");
            nombre = entrada.nextLine();
            if (!nombre.isEmpty()) break;
            System.out.println("El nombre no puede estar vacío.");
        }

        TipoAula tipo = null;
        while (tipo == null) {
            System.out.println("Seleccione el tipo de aula:");
            TipoAula[] tipos = TipoAula.values();
            for (int i = 0; i < tipos.length; i++) {
                System.out.println((i + 1) + ". " + tipos[i]);
            }
            try {
                int opcionTipo = entrada.nextInt();
                entrada.nextLine();
                if (opcionTipo >= 1 && opcionTipo <= tipos.length) {
                    tipo = tipos[opcionTipo - 1];
                } else {
                    System.out.println("Opción de tipo no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                entrada.nextLine();
            }
        }

        int capacidad = -1;
        while (capacidad <= 0) {
            System.out.print("Capacidad: ");
            try {
                capacidad = entrada.nextInt();
                entrada.nextLine();
                if (capacidad <= 0) System.out.println("La capacidad debe ser mayor a 0.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                entrada.nextLine();
            }
        }

        aulas.add(new Aula(aulaId++, nombre, tipo, capacidad));
        System.out.println("Aula registrada correctamente.");
    }

    // Listar aulas
    static void listarAulas() {
        if (aulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
        } else {
            aulas.forEach(System.out::println);
        }
    }

    // Registrar reserva con validación independiente por dato
    static void registrarReserva() {
        Aula aula = null;
        while (aula == null) {
            System.out.print("ID del aula: ");
            try {
                int id = entrada.nextInt();
                entrada.nextLine();
                aula = aulas.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
                if (aula == null) System.out.println("Aula no encontrada.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                entrada.nextLine();
            }
        }

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Fecha (YYYY-MM-DD): ");
            try {
                fecha = LocalDate.parse(entrada.nextLine());
                if (fecha.isBefore(LocalDate.now())) {
                    System.out.println("La fecha no puede ser anterior a hoy.");
                    fecha = null;
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto.");
            }
        }

        LocalTime hi = null;
        while (hi == null) {
            System.out.print("Hora inicio (HH:MM): ");
            try { hi = LocalTime.parse(entrada.nextLine()); }
            catch (Exception e) { System.out.println("Formato de hora incorrecto."); }
        }

        LocalTime hf = null;
        while (hf == null) {
            System.out.print("Hora fin (HH:MM): ");
            try {
                hf = LocalTime.parse(entrada.nextLine());
                if (!hf.isAfter(hi)) {
                    System.out.println("Hora fin debe ser posterior a hora inicio.");
                    hf = null;
                }
            } catch (Exception e) {
                System.out.println("Formato de hora incorrecto.");
            }
        }

        String resp;
        while (true) {
            System.out.print("Responsable: ");
            resp = entrada.nextLine();
            if (!resp.isEmpty()) break;
            System.out.println("El nombre del responsable no puede estar vacío.");
        }

        int opcionReserva = 0;
        while (opcionReserva < 1 || opcionReserva > 3) {
            System.out.println("Seleccione el tipo de reserva:");
            System.out.println("1. CLASE");
            System.out.println("2. PRACTICA");
            System.out.println("3. EVENTO");
            try {
                opcionReserva = entrada.nextInt();
                entrada.nextLine();
                if (opcionReserva < 1 || opcionReserva > 3) System.out.println("Opción inválida.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                entrada.nextLine();
            }
        }

        Reserva r = null;
        switch (opcionReserva) {
            case 1: r = new ReservaClase(reservaId, aula, fecha, hi, hf, resp); break;
            case 2: r = new ReservaPractica(reservaId, aula, fecha, hi, hf, resp); break;
            case 3:
                TipoEvento te = null;
                while (te == null) {
                    System.out.println("Seleccione tipo de evento:");
                    TipoEvento[] eventos = TipoEvento.values();
                    for (int i = 0; i < eventos.length; i++)
                        System.out.println((i + 1) + ". " + eventos[i]);
                    try {
                        int opcionEvento = entrada.nextInt();
                        entrada.nextLine();
                        if (opcionEvento >= 1 && opcionEvento <= eventos.length)
                            te = eventos[opcionEvento - 1];
                        else System.out.println("Opción de evento no válida.");
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida.");
                        entrada.nextLine();
                    }
                }
                r = new ReservaEvento(reservaId, aula, fecha, hi, hf, resp, te);
                break;
        }

        if (r != null) {
            if (!r.validar()) {
                System.out.println("Error: Reserva no válida (horario o datos incorrectos).");
                return;
            }
            if (ReservaUtils.hayConflicto(r, reservas)) {
                System.out.println("Error: Conflicto de horario con otra reserva activa.");
                return;
            }
            reservas.add(r);
            reservaId++;
            System.out.println("Reserva registrada correctamente.");
        }
    }

    // Listar reservas
    static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }

    // Cancelar reserva
    static void cancelarReserva() {
        while (true) {
            System.out.print("ID de la reserva a cancelar: ");
            try {
                int id = entrada.nextInt();
                entrada.nextLine();
                Reserva r = reservas.stream()
                        .filter(res -> res.getId() == id && res.getEstado().equals("ACTIVA"))
                        .findFirst().orElse(null);
                if (r == null) System.out.println("Reserva no encontrada o ya cancelada.");
                else { r.cancelar(); System.out.println("Reserva cancelada."); }
                break;
            } catch (InputMismatchException e) {
                System.out.println("ID inválido.");
                entrada.nextLine();
            }
        }
    }

    // Buscar reservas por responsable
    static void buscarPorResponsable() {
        System.out.print("Nombre del responsable: ");
        String nombre = entrada.nextLine();
        ArrayList<Reserva> resultado = ReservaUtils.buscarPorResponsable(nombre, reservas);
        if (resultado.isEmpty()) System.out.println("No se encontraron reservas.");
        else resultado.forEach(System.out::println);
    }

    // Generar reportes CSV
    static void generarReportes() {
        File carpeta = new File("data");
        if (!carpeta.exists()) carpeta.mkdir();
        String rutaCSV = "data/ReporteReservas.csv";
        try {
            GenerarReportes.exportarReporteCompletoCSV(reservas, rutaCSV);
            System.out.println("Reporte exportado correctamente a " + rutaCSV);
        } catch (Exception e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
}