package com.electiva.kellyhuber.listadetareas.dominio;

import com.electiva.kellyhuber.listadetareas.Modelos.Usuario;

/**
 * Created by kelly on 30/10/2017.
 */

public interface ILUsuario {

    void crearUsuario(String password, Usuario usuario, CallbackInteractor<String> interactor);

    void autenticaUsuario(String password, String email, CallbackInteractor<String> interactor);

    void recuperarPassword(String email, CallbackInteractor<String> interactor);
}
