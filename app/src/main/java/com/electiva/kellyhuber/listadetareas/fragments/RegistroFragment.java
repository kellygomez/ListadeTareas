package com.electiva.kellyhuber.listadetareas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.electiva.kellyhuber.listadetareas.Presentador.IRegistroPresenter;
import com.electiva.kellyhuber.listadetareas.Presentador.RegistroPresenter;
import com.electiva.kellyhuber.listadetareas.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroFragment extends Fragment implements IRegistroFragment {

    private IRegistroPresenter rpresenter;
    private OnRegistroInteractionListener listener;

    @BindView(R.id.txtLogin)
    TextView txtLoging;

    @BindView(R.id.txtNombreApellidos)
    EditText txtNombreUsuario;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    public RegistroFragment() {
    }

    public static RegistroFragment newInstance() {
        RegistroFragment fragment = new RegistroFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        ButterKnife.bind(this,view);

        txtLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               irALogin();
            }
        });

        rpresenter = new RegistroPresenter(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegistroInteractionListener){
            listener = (OnRegistroInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " must implement OnRegistroInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //METODOS IMPLEMENTADOS DE LA INTERFACE IRegistroFragment

    @OnClick(R.id.btnRegistrar)
    public void registrarUsuario(){
        String nombreApellido = txtNombreUsuario.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        rpresenter.registroUsuario(nombreApellido,email,password);
    }


    @Override
    public void habilitarControles() {
        txtEmail.setEnabled(true);
        txtNombreUsuario.setEnabled(true);
        txtPassword.setEnabled(true);
        btnRegistrar.setEnabled(true);
    }

    @Override
    public void deshabilitarControles() {
        txtEmail.setEnabled(false);
        txtNombreUsuario.setEnabled(false);
        txtPassword.setEnabled(false);
        btnRegistrar.setEnabled(false);
    }

    @Override
    public void mostrarProgress() {

    }

    @Override
    public void ocultarProgress() {

    }

    @Override
    public void mostrarError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void finalizarRegistro() {
        if (listener != null) {
            listener.finalizarRegistro();
        }
    }

    @Override
    public void irALogin() {
        if (listener != null) {
            listener.lanzarLoging();
        }
    }




    public interface OnRegistroInteractionListener {

        void lanzarLoging();

        void finalizarRegistro();

    }
}
