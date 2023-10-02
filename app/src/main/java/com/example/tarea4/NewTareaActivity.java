package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.example.tarea4.databinding.ActivityNewTareaBinding;

public class NewTareaActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "wordlistsql.REPLY";
    private ActivityNewTareaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewTareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button button = binding.buttonSave;
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(binding.editTarea.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = binding.editTarea.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }
}