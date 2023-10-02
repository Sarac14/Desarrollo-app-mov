package com.example.tarea4.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_tarea")
public class Tarea {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String tarea;
    private boolean completada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTarea() {
        return tarea;
    }

    public  Tarea (@NonNull String tarea, boolean completada) {

        this.tarea = tarea;
        this.completada = completada;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }


}
