package com.example.tarea_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tarea_2.databinding.ActivityRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding binding;
    RecyclerAdapter adapter;
    public static List<Tarea> tareas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recycler;

        int spanCount = 1;

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 2;
        }

        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));

        //students = new ArrayList<>();
        adapter = new RecyclerAdapter(tareas);
        recyclerView.setAdapter(adapter);

        //recyclerView.setAdapter(new StudentAdapter2(Student.getStudents()));

        binding.btnNewTarea.setOnClickListener(v -> {
            String tarea = binding.txtNewTarea.getText().toString();
            if (!tarea.isEmpty()) {
                tareas.add(new Tarea(tarea, R.drawable.bombilla ));
                adapter.notifyDataSetChanged();
                binding.txtNewTarea.setText("");
            } else {
                Toast.makeText(RecyclerActivity.this, "Campo vacio", Toast.LENGTH_SHORT).show();
            }
        });

    }

}