package com.example.tarea4.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tarea4.entities.Tarea;

import java.util.List;

public class TareaViewModel extends AndroidViewModel {
    private TareaRepositorio mRepositorio;

    private final LiveData<List<Tarea>> mAllTareas;

    public TareaViewModel(Application application){
        super(application);
        mRepositorio = new TareaRepositorio(application);
        mAllTareas = mRepositorio.getAllTareas();
    }

    public LiveData<List<Tarea>> getAllTareas () {return mAllTareas;}
    public void insert(Tarea tarea) {mRepositorio.insert(tarea);}

    public void update (Tarea tarea){
        mRepositorio.update(tarea);
    }
    public void eliminar (Tarea tarea) {mRepositorio.eliminar(tarea);}
}
