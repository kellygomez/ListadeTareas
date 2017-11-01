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

import com.electiva.kellyhuber.listadetareas.Presentador.ILoginPresenter;
import com.electiva.kellyhuber.listadetareas.Presentador.LoginPresenter;
import com.electiva.kellyhuber.listadetareas.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnLoginInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements ILoginFragment {


    private ILoginPresenter loginPresenter;
    private OnLoginInteractionListener mListener;

    //vistas del fragmento
    @BindView(R.id.txtUsuario)
    EditText txtUsuario;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtRegistrate)
    TextView txtRegistrate;

    @BindView(R.id.btnIngresar)
    Button btnIngresar;

    @BindView(R.id.txtRecuperarPass)
    TextView textRecuperarPassword;


    public LoginFragment() {
        // Required empty public constructor
    }

    //crea una nueva instancia de este fragmento
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    //se asocia el layaout con el fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irARegistro();
            }
        });

        textRecuperarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRecuperar();
            }
        });

        loginPresenter = new LoginPresenter(this);

        return view;
    }

    //metodo llamado por el sistema cuando se agrega el fragmento a la actividad que lo contiene
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // IMPLEMENTACION DE LOS METODOS DE ILoginFragment
    @Override
    public void habilitarVistas() {
        txtUsuario.setEnabled(true);
        txtPassword.setEnabled(true);
        txtRegistrate.setEnabled(true);
        textRecuperarPassword.setEnabled(true);
        btnIngresar.setEnabled(true);
    }

    @Override
    public void deshabilitarVistas() {
        txtUsuario.setEnabled(false);
        txtPassword.setEnabled(false);
        txtRegistrate.setEnabled(false);
        textRecuperarPassword.setEnabled(false);
        btnIngresar.setEnabled(false);
    }

    @Override
    public void mostrarProgress() {

    }

    @Override
    public void ocultarProgress() {

    }

    @Override
    public void finalizarLogin() {
        if (mListener != null) {
            mListener.finalizarLogin();
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
    }

    //la actividad que implementa la interface OnLoginInteractionListener
    // contenida en mListener llama el metodo lazarRegistro que implementó
    @Override
    public void irARegistro() {
        if (mListener != null) {
            mListener.LanzarRegistro();
        }
    }

    @OnClick(R.id.btnIngresar)
    @Override
    public void ingresar() {
        String email = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();

        loginPresenter.autenticar(email,password);
    }

    @Override
    public void lanzarRecuperar() {
        if (mListener != null) {
            mListener.irArecuperarPassword();
        }
    }

    /*
         * interfaz implementada por la activity que contiene este fragmento para permitir la
         * comunicacion entre este fragmento y la activity y otros posibles fragmentos
         */
    public interface OnLoginInteractionListener {
        void finalizarLogin();

        void LanzarRegistro();

        void irArecuperarPassword();
    }
}
