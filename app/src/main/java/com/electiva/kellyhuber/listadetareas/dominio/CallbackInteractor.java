package com.electiva.kellyhuber.listadetareas.dominio;

/**
 * Created by kelly on 30/10/2017.
 */

public interface CallbackInteractor<T> {

    void success(T data);

    void error(String error);
}
