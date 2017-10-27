package com.electiva.kellyhuber.listadetareas.Presentador;

import com.electiva.kellyhuber.listadetareas.Modelos.AppDataBase;
import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;
import com.electiva.kellyhuber.listadetareas.Vistas.ILisView;

import java.util.List;

/**
 * Created by kelly on 23/10/2017.
 */

public class TareaPresenter implements ITareaPresenter {

    AppDataBase baseDatos;
    List<Tarea> listTareas;
    //LTareas ltareas;
    ILisView view;

    public TareaPresenter(ILisView view, AppDataBase baseDeDatos) {
        //this.ltareas = new LTareas();
        this.view = view;
        baseDatos = baseDeDatos;
    }


    public void addTarea(final String descTarea, final String fecha) {

        //ltareas.addTarea(objTarea);
       /* AsyncTask<String,Void,Void> task = new AsyncTask<String,Void,Void>(){
            final String desc = descTarea;
            final String date = fecha;
            @Override
            protected Void doInBackground(String... String) {
                Tarea objTarea = new Tarea();
                objTarea.setDescripcion(desc);
                objTarea.setFecha(date);
                objTarea.setRealizada(false);
                baseDatos.iLtarea().insertTarea(objTarea);
                return null;
            }
        };

        task.execute();*/
        Tarea objTarea = new Tarea();
        objTarea.setDescripcion(descTarea);
        objTarea.setFecha(fecha);
        objTarea.setRealizada(false);
        baseDatos.iLtarea().insertTarea(objTarea);
        listTareas = obtenerTareas();
        view.refrescarListaTareas(listTareas,listTareas.size());

    }


    public List<Tarea> obtenerTareas() {
        /*List<Tarea> tareaList = new ArrayList<>();

        try {
            AsyncTask<Void,Void,List<Tarea>> task = new AsyncTask<Void,Void,List<Tarea>>(){

                @Override
                protected List<Tarea> doInBackground(Void... voids) {

                    return baseDatos.iLtarea().selectAllListaTareas();
                }

                @Override
                protected void onPostExecute(List<Tarea> list) {
                    super.onPostExecute(list);
                }
            };
            task.execute();
            tareaList = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return baseDatos.iLtarea().selectAllListaTareas();
        //return ltareas.getListaTareas();
        return tareaList;*/
        return baseDatos.iLtarea().selectAllListaTareas();
    }

    //es nuevo
    public void itemCambioEstado(int posicion, boolean realizado) {
        listTareas = obtenerTareas();
        //Tarea tarea = ltareas.getListaTareas().get(posicion);
        Tarea tarea = listTareas.get(posicion);
        tarea.setRealizada(realizado);
        //baseDatos.iLtarea().updateCambioEstado(tarea);
        //ltareas.cambioEstado(posicion,tarea);

        /*AsyncTask<Tarea,Void,Void> task = new AsyncTask<Tarea,Void,Void>(){
            Tarea tr=tarea;
            @Override
            protected Void doInBackground(Tarea... tareas) {
                baseDatos.iLtarea().updateCambioEstado(tr);
                return null;
            }
        };
        task.execute();*/
        baseDatos.iLtarea().updateCambioEstado(tarea);
        listTareas = obtenerTareas();
        view.refrescarListaTareas(listTareas, posicion);
        //view.refrescarTarea(posicion);
    }
}
