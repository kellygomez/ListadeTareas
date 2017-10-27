package com.electiva.kellyhuber.listadetareas.Modelos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.electiva.kellyhuber.listadetareas.dominio.ILtarea;

/**
 * Created by kelly on 24/10/2017.
 */

@Database(entities = {Tarea.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ILtarea iLtarea();
}
