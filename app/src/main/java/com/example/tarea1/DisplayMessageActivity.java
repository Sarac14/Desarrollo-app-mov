package com.example.tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tarea1.databinding.ActivityDisplayMessageBinding;

public class DisplayMessageActivity extends AppCompatActivity {


    ActivityDisplayMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = intent.getStringExtra("value");

        binding.showMessage.setText(message);

        binding.btnNuevo.setOnClickListener(v -> {
            Intent nuevo = new Intent(DisplayMessageActivity.this, MainActivity.class);
            startActivity(nuevo);
        });
    }

}