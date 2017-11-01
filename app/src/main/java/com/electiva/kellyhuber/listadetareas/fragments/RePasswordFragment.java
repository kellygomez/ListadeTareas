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

import com.electiva.kellyhuber.listadetareas.Presentador.IRePasswordPresenter;
import com.electiva.kellyhuber.listadetareas.Presentador.RePasswordPresenter;
import com.electiva.kellyhuber.listadetareas.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RePasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RePasswordFragment extends Fragment implements IRePasswordFragment {

    private IRePasswordPresenter passwordPresenter;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.textEmail)
    EditText txtEmail;

    @BindView(R.id.btnRecuperar)
    Button btnRecuperar;

    public RePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */

    public static RePasswordFragment newInstance() {
        RePasswordFragment fragment = new RePasswordFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_re_password, container, false);
        ButterKnife.bind(this,view);

        passwordPresenter = new RePasswordPresenter(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    //IMPLEMENTACION METODOS DE LA INTERFACE IRePasswordFragment
    @OnClick(R.id.btnRecuperar)
    @Override
    public void recuperarPassword() {
        String email = txtEmail.getText().toString();
        passwordPresenter.recuperarPassword(email);
    }

    @Override
    public void habilitarControles() {
        txtEmail.setEnabled(true);
        btnRecuperar.setEnabled(true);
    }

    @Override
    public void deshabilitarControles() {
        txtEmail.setEnabled(false);
        btnRecuperar.setEnabled(false);
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
    public void finalizarRePassword() {
        if (mListener != null) {
            mListener.finalizarRePassword();
        }
    }

    @Override
    public void irALogin() {
        if (mListener != null) {
            mListener.lanzarLogin();
        }
    }

    @Override
    public void mostrarMensajeExito() {
        Snackbar.make(getView(), R.string.mensaje_recuperacion, Snackbar.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void finalizarRePassword();

        void lanzarLogin();
    }
}
