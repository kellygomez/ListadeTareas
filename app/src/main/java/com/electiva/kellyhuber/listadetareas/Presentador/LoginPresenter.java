package com.electiva.kellyhuber.listadetareas.Presentador;

import com.electiva.kellyhuber.listadetareas.dominio.CallbackInteractor;
import com.electiva.kellyhuber.listadetareas.dominio.ILUsuario;
import com.electiva.kellyhuber.listadetareas.dominio.LUsuario;
import com.electiva.kellyhuber.listadetareas.fragments.ILoginFragment;

/**
 * Created by kelly on 30/10/2017.
 */

public class LoginPresenter implements ILoginPresenter{

    private ILoginFragment view;
    private ILUsuario Lusuario;

    public LoginPresenter(ILoginFragment view) {
        this.view = view;
        Lusuario = new LUsuario();
    }

    @Override
    public void autenticar(String email, String password) {
        view.deshabilitarVistas();

        if (email.isEmpty()||password.isEmpty()){
            view.mostrarError("ingrese todos los campos");
            view.habilitarVistas();
        }else{
            Lusuario.autenticaUsuario(password, email, new CallbackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarVistas();
                    view.finalizarLogin();
                }

                @Override
                public void error(String error) {
                    view.habilitarVistas();
                    view.mostrarError(error);
                }
            });
        }
    }
}
