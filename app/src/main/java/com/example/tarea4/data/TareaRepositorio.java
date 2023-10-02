package com.example.tarea4.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tarea4.entities.Tarea;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TareaRepositorio {

    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    private TareaDao mTareaDao;
    private LiveData<List<Tarea>> mAllTareas;
    TareaRepositorio(Application application){
        TareaRoomDatabase db = TareaRoomDatabase.getDatabase(application);
        mTareaDao = db.tareaDao();
        mAllTareas = mTareaDao.getAlphabetizedWords();
    }

    LiveData<List<Tarea>> getAllTareas () {return mAllTareas;}
    void insert(Tarea tarea){
        TareaRoomDatabase.databaseWriterExecutor.execute(() ->{
            mTareaDao.insert(tarea);
        });
    }

    public void update(Tarea tarea) {
        new Thread(() -> {
            mTareaDao.updateTarea(tarea);
        }).start();
    }

    public void eliminar(Tarea tarea) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTareaDao.eliminar(tarea);
            }
        });    }
}
