package com.electiva.kellyhuber.listadetareas.Modelos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kelly on 22/10/2017.
 */
@Entity
public class Tarea {

    @PrimaryKey(autoGenerate = true)
    int id;

    private String descripcion;
    private String fecha;
    private boolean realizada;


    public Tarea() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
