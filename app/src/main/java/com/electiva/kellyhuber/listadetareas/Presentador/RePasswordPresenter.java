package com.electiva.kellyhuber.listadetareas.Presentador;

import com.electiva.kellyhuber.listadetareas.dominio.CallbackInteractor;
import com.electiva.kellyhuber.listadetareas.dominio.ILUsuario;
import com.electiva.kellyhuber.listadetareas.dominio.LUsuario;
import com.electiva.kellyhuber.listadetareas.fragments.IRePasswordFragment;

/**
 * Created by kelly on 31/10/2017.
 */

public class RePasswordPresenter implements IRePasswordPresenter {

    IRePasswordFragment view;
    ILUsuario Lusuario;

    public RePasswordPresenter(IRePasswordFragment view) {
        this.view = view;
        Lusuario = new LUsuario();
    }


    @Override
    public void recuperarPassword(String email) {
        view.deshabilitarControles();
        if (email.isEmpty()){
            view.mostrarError("ingrese todos los campos");
            view.habilitarControles();
        }else{
            Lusuario.recuperarPassword(email, new CallbackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarControles();
                    view.mostrarMensajeExito();
                    view.finalizarRePassword();
                }

                @Override
                public void error(String error) {
                    view.habilitarControles();
                    view.mostrarError(error);
                }
            });
        }
    }
}
