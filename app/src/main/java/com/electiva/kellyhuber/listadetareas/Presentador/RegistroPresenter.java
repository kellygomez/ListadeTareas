package com.electiva.kellyhuber.listadetareas.Presentador;

import com.electiva.kellyhuber.listadetareas.Modelos.Usuario;
import com.electiva.kellyhuber.listadetareas.dominio.CallbackInteractor;
import com.electiva.kellyhuber.listadetareas.dominio.ILUsuario;
import com.electiva.kellyhuber.listadetareas.dominio.LUsuario;
import com.electiva.kellyhuber.listadetareas.fragments.IRegistroFragment;

/**
 * Created by kelly on 30/10/2017.
 */

public class RegistroPresenter implements IRegistroPresenter {

    private IRegistroFragment view;
    private ILUsuario Lusuario;



    public RegistroPresenter(IRegistroFragment view){
        this.view = view;
        Lusuario = new LUsuario();
    }

    @Override
    public void registroUsuario(String nombre, String email, String password) {
        view.deshabilitarControles();
        if(nombre.isEmpty() || email.isEmpty() || password.isEmpty()){
            view.mostrarError("ingrese todos los campos");
            view.habilitarControles();
        }else{
            Usuario usuario = new Usuario(nombre, email);
            Lusuario.crearUsuario(password, usuario, new CallbackInteractor<String>() {
                @Override
                public void success(String data) {
                    view.habilitarControles();
                    view.finalizarRegistro();
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
