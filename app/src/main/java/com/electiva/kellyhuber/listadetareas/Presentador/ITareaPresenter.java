package com.electiva.kellyhuber.listadetareas.Presentador;

import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;

import java.util.List;

/**
 * Created by kelly on 24/10/2017.
 */

public interface ITareaPresenter {


    public void addTarea(String descTarea, String fecha);

    public List<Tarea> obtenerTareas();

    public void itemCambioEstado(int posicion, boolean realizado);
}
