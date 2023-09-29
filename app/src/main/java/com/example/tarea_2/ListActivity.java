package com.example.tarea_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tarea_2.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ActivityListBinding binding;
    public static List<Tarea> tareas = new ArrayList<>();
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //tareas = new ArrayList<>();
        adapter = new ListAdapter(this, tareas);
        binding.lvUsers.setAdapter(adapter);

        binding.btnAgregar.setOnClickListener(v -> {
            String tarea = binding.txtTarea.getText().toString();
            if (!tarea.isEmpty()) {
                tareas.add(new Tarea(tarea,R.drawable.bombilla));
                adapter.notifyDataSetChanged();
                binding.txtTarea.setText("");
            } else {
                Toast.makeText(ListActivity.this, "Campo vacio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}