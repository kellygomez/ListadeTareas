package com.electiva.kellyhuber.listadetareas.fragments;

/**
 * Created by kelly on 30/10/2017.
 */

public interface IRePasswordFragment {
    void habilitarControles();
    void deshabilitarControles();
    void mostrarProgress();
    void ocultarProgress();
    void mostrarError(String error);
    void mostrarMensajeExito();
    void finalizarRePassword();
    void irALogin();
    void recuperarPassword();
}
