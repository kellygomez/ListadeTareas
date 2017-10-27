package com.electiva.kellyhuber.listadetareas.dominio;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;

import java.util.List;

/**
 * Created by kelly on 24/10/2017.
 */
@Dao
public interface ILtarea {

    @Insert
    public void insertTarea (Tarea tarea);

    @Insert
    public void insertTareaTodo (List<Tarea> listTarea);

    @Query("SELECT * FROM Tarea")
    public List<Tarea> selectAllListaTareas();

    @Update
    public void updateCambioEstado(Tarea tarea);
}
