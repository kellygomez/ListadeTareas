package com.electiva.kellyhuber.listadetareas.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.electiva.kellyhuber.listadetareas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtUsuario)
    EditText txtUsuario;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtRegistrate)
    TextView txtRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        //crea un metodo para cuando se haga click en el texto
        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);

                startActivity(intent);
            }
        });

    }

    //captura datos de usuario e ingrsa a la aplicacion
    public void ingresar(View view) {
        Intent intent = new Intent(this,ListaTareasActivity.class);

        startActivity(intent);
    }
}
