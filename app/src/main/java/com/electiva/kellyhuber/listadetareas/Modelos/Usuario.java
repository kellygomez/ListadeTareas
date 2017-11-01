package com.electiva.kellyhuber.listadetareas.Modelos;

/**
 * Created by kelly on 30/10/2017.
 */

public class Usuario {

    private String Uid;
    private String nombres;
    private String email;

    public Usuario() {
    }

    public Usuario(String nombres, String email) {
        this.nombres = nombres;
        this.email = email;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
