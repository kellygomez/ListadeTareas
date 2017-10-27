package com.electiva.kellyhuber.listadetareas.Vistas;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.electiva.kellyhuber.listadetareas.Modelos.AppDataBase;
import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;
import com.electiva.kellyhuber.listadetareas.Presentador.TareaPresenter;
import com.electiva.kellyhuber.listadetareas.R;
import com.electiva.kellyhuber.listadetareas.adaptadores.ListaTareasAdapter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaTareasActivity extends AppCompatActivity implements ILisView, ListaTareasAdapter.OnListenerItemCheck{

    TareaPresenter tareaPresenter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    EditText editTarea;
    EditText editFecha;
    Button btnAgregar;
    AppDataBase baseDatos;

    List<Tarea> listTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        baseDatos = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"lista_tareas").allowMainThreadQueries().build();

        ButterKnife.bind(this);

        tareaPresenter = new TareaPresenter(this, baseDatos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        listTareas = tareaPresenter.obtenerTareas();

        recyclerView.setAdapter(new ListaTareasAdapter(listTareas,this));


    }


    @OnClick(R.id.fab)
    public void nuevaTarea(){
        //crea la PopupWindow para agregar una tarea

        AlertDialog.Builder popupview = new AlertDialog.Builder(ListaTareasActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.agrega_tarea,null);

        editTarea = (EditText)view1.findViewById(R.id.edtTarea);
        editFecha = (EditText)view1.findViewById(R.id.edtFecha);
        btnAgregar = (Button)view1.findViewById(R.id.btnAgregar);

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarFecha();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agregarTarea()){
                    Toast.makeText(ListaTareasActivity.this,"Ingrese todos los campo",Toast.LENGTH_SHORT).show();
                }
            }
        });

        popupview.setView(view1);
        final AlertDialog dialogo = popupview.create();
        dialogo.show();
    }

    //@OnClick(R.id.btnAgregar)
    @Override
    public boolean agregarTarea() {
        //se envia los datos capturados de la descripcion y fecha al presenter
        String descripcion = editTarea.getText().toString();
        String fecha = editFecha.getText().toString();
        if(descripcion.isEmpty() && fecha.isEmpty()){
            return true;
        }else {
            tareaPresenter.addTarea(descripcion, fecha);
            editTarea.setText("");
            editFecha.setText("");
            return false;
        }
    }

    public void agregarFecha(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int theyear, int month, int day) {
                    editFecha.setText(theyear+"-"+month+1+"-"+day);
            }
        },dia,mes,year);
        datePickerDialog.show();
    }

    @Override
    public void refrescarListaTareas(List<Tarea> listaTareas, int posicion) {

        listTareas = tareaPresenter.obtenerTareas();
        recyclerView.setAdapter(new ListaTareasAdapter(listTareas,this));
        recyclerView.scrollToPosition(posicion);
    }

    @Override
    public void refrescarTarea(int posicion) {
        recyclerView.getAdapter().notifyItemChanged(posicion);
    }

    public void itemCambioEstado(int posicion, boolean realizada) {
        tareaPresenter.itemCambioEstado(posicion, realizada);
    }

}
