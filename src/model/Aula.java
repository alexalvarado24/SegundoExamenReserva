/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import enums.TipoAula;
import java.util.Objects;
/**
 *
 * @author Emir Alvarado
 */
public class Aula {
    private int id;
    private String nombre;
    private TipoAula tipo;
    private int capacidad;

    public Aula(int id, String nombre, TipoAula tipo, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoAula getTipo() { return tipo; }
    public void setTipo(TipoAula tipo) { this.tipo = tipo; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() {
        return "Aula [" + "ID: " + id + ", Nombre: '" + nombre + '\'' + ", Tipo: " + tipo + ", Capacidad: " + capacidad + ']';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Aula)) return false;
        Aula aula = (Aula) o;
        return id == aula.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}