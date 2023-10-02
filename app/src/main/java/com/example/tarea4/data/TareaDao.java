package com.example.tarea4.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tarea4.entities.Tarea;

import java.util.List;

@Dao
public interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Tarea tarea);

    @Query("DELETE FROM tabla_tarea")
    void deleteAll();
    @Query("SELECT * FROM tabla_tarea ORDER BY tarea ASC")
    LiveData<List<Tarea>> getAlphabetizedWords();

    @Update
    void updateTarea(Tarea tarea);
    @Delete
    void eliminar(Tarea tarea);
}
