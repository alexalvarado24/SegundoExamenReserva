/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import interfaces.Validable;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Emir Alvarado
 */
//La clase se extiende a la interfaz para validar
public abstract class Reserva implements Validable { //Clase abstracta no se puede instanciar directamente
    
    //Atributos encapsulados
    protected int id;
    protected Aula aula;
    protected LocalDate fecha;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    protected String responsable;
    protected String estado; // ACTIVA, CANCELADA
//Constructor
    public Reserva(int id, Aula aula, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String responsable) {
        this.id = id;
        this.aula = aula;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.responsable = responsable;
        this.estado = "ACTIVA";
    }
//Getters y setters
    public int getId() { return id; }
    public Aula getAula() { return aula; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public String getResponsable() { return responsable; }
    public String getEstado() { return estado; }
    public void cancelar() { estado = "CANCELADA"; }
    
// Método de la interfaz Validable que será implementado por cada subclase
    @Override
    public abstract boolean validar();

    @Override
    public String toString() {
        return "Reserva [" + "ID: " + id + ", Aula: " + aula.getNombre() + ", Fecha: " + fecha + ", Hora de Inicio: " + horaInicio +
                ", Hora de Fin: " + horaFin + ", Responsable: '" + responsable + '\'' + ", Estado: '" + estado + '\'' + ']';
    }
}