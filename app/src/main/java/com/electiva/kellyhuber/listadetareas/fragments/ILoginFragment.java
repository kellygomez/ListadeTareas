package com.electiva.kellyhuber.listadetareas.fragments;

/**
 * Created by kelly on 30/10/2017.
 */

public interface ILoginFragment {
    void habilitarVistas();
    void deshabilitarVistas();
    void mostrarProgress();
    void ocultarProgress();
    void finalizarLogin();
    void mostrarError(String mensaje);
    void ingresar();
    void irARegistro();
    void lanzarRecuperar();
}
