package com.example.tarea4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tarea4.adapters.RecyclerAdapter;
import com.example.tarea4.data.TareaViewModel;
import com.example.tarea4.databinding.ActivityMainBinding;
import com.example.tarea4.entities.Tarea;

public class MainActivity extends AppCompatActivity {

    private TareaViewModel mTareaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recycler;

        int spanCount = 1;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 2;
        }
        recyclerView.setHasFixedSize(true);

        mTareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);


        final RecyclerAdapter adapter = new RecyclerAdapter(mTareaViewModel, new RecyclerAdapter.TareaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));


        mTareaViewModel.getAllTareas().observe(this, tareas ->{
            adapter.submitList(tareas);
        });

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewTareaActivity.class);
            tareaActivityResultLauncher.launch(intent);
        });


    }

    ActivityResultLauncher<Intent> tareaActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    Tarea tarea = new Tarea(data.getStringExtra(NewTareaActivity.EXTRA_REPLY),false);
                    mTareaViewModel.insert(tarea);
                } else {
                    Toast.makeText(getApplicationContext(), "Campo vacio", Toast.LENGTH_LONG).show();
                }
            });
}