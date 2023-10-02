package com.example.tarea4.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tarea4.entities.Tarea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tarea.class}, version = 1, exportSchema = false)
public abstract class TareaRoomDatabase extends RoomDatabase {
    public abstract TareaDao tareaDao();

    private static volatile TareaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TareaRoomDatabase getDatabase (final Context context){
        if(INSTANCE == null){
            synchronized (TareaRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TareaRoomDatabase.class,"Tarea_DB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
