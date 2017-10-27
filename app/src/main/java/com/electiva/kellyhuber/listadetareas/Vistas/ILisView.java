package com.electiva.kellyhuber.listadetareas.Vistas;

import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;

import java.util.List;

/**
 * Created by kelly on 23/10/2017.
 */

public interface ILisView {

    boolean agregarTarea();

    void refrescarListaTareas(List<Tarea> listaTareas, int position);

    void refrescarTarea(int posicion);

}
