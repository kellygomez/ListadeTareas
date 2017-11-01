package com.electiva.kellyhuber.listadetareas.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.electiva.kellyhuber.listadetareas.R;
import com.electiva.kellyhuber.listadetareas.fragments.LoginFragment;
import com.electiva.kellyhuber.listadetareas.fragments.RePasswordFragment;
import com.electiva.kellyhuber.listadetareas.fragments.RegistroFragment;

public class AutenticarActivity extends AppCompatActivity implements LoginFragment.OnLoginInteractionListener,
RegistroFragment.OnRegistroInteractionListener, RePasswordFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);

        inicializarFragment();

    }

    public void inicializarFragment(){
        FragmentTransaction transaccionn =
                getSupportFragmentManager().beginTransaction();

        transaccionn.replace(R.id.frame_autenticar, LoginFragment.newInstance());
        transaccionn.commit();
    }


    //IMPLEMENTACION DE METODOS DE LoginFragment.OnLoginInteractionListener
    @Override
    public void finalizarLogin() {
        Intent intent = new Intent(this, ListaTareasActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LanzarRegistro() {
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();

        transaccion.replace(R.id.frame_autenticar, RegistroFragment.newInstance());
        transaccion.commit();
    }

    @Override
    public void irArecuperarPassword() {
        inicializarFragment();
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();

        transaccion.replace(R.id.frame_autenticar, RePasswordFragment.newInstance());
        transaccion.commit();
    }

    //IMPLEMENTACION DE METODOS DE RegistroFragment.OnRegistroInteractionListener
    @Override
    public void lanzarLoging() {
        inicializarFragment();
        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();

        transaccion.replace(R.id.frame_autenticar, LoginFragment.newInstance());
        transaccion.commit();
    }

    @Override
    public void finalizarRegistro() {
        Intent intent = new Intent(this, ListaTareasActivity.class);
        startActivity(intent);
        finish();
    }

    //IMPLEMENTACION DE METODOS DE RePasswordFragment.OnFragmentInteractionListener
    @Override
    public void finalizarRePassword() {
        inicializarFragment();
    }


    @Override
    public void lanzarLogin() {

    }
}
