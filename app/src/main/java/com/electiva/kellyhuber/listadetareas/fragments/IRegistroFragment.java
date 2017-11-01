package com.electiva.kellyhuber.listadetareas.fragments;

/**
 * Created by kelly on 30/10/2017.
 */

public interface IRegistroFragment {
    void habilitarControles();
    void deshabilitarControles();
    void mostrarProgress();
    void ocultarProgress();
    void mostrarError(String error);
    void finalizarRegistro();
    void irALogin();
    void registrarUsuario();
}
