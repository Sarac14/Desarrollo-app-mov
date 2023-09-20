package com.example.tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tarea1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String selectedGenero = "Masculino";
    private Calendar fechaSelec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<String> genero = new ArrayList<>();
        genero.add("Masculino");
        genero.add("Femenino");

        ArrayAdapter adp = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, genero);
         binding.spinner.setAdapter(adp);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGenero = (String) binding.spinner.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        EditText etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.etPlannedDate) {
                    showDatePickerDialog();
                }
            }
            private void showDatePickerDialog() {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        etPlannedDate.setText(selectedDate);

                        fechaSelec = Calendar.getInstance();
                        fechaSelec.set(year, month, day);

                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdbSi) {
                    binding.panel2.setVisibility(View.VISIBLE);
                } else {
                    binding.panel2.setVisibility(View.GONE);
                }
            }
        });


        binding.btnEnviar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);

            String secondMessage = "No me gusta la programacion";
            List<String> selectedLanguages = new ArrayList<>();

            if(binding.rdbSi.isChecked() == true){

                if (binding.cbxJava.isChecked()) {
                    selectedLanguages.add("Java");
                }
                if (binding.cbxCpp.isChecked()) {
                    selectedLanguages.add("C/C++");
                }
                if (binding.cbxCsharp.isChecked()) {
                    selectedLanguages.add("C#");
                }
                if (binding.cbxGo.isChecked()) {
                    selectedLanguages.add("Go Land");
                }
                if (binding.cbxJs.isChecked()) {
                    selectedLanguages.add("JS");
                }
                if (binding.cbxPython.isChecked()) {
                    selectedLanguages.add("Python");
                }
                secondMessage = "Me gusta programar. Mis lenguajes favoritos son: " + TextUtils.join(", ", selectedLanguages) + ".";

            }
            if (binding.txtNombre.getText().toString().trim().isEmpty() || binding.txtApellido.getText().toString().trim().isEmpty() || (binding.rdbSi.isChecked() && selectedLanguages.isEmpty())) {
                Toast.makeText(MainActivity.this, "Error: algunos campos obligatorios no han sido completados", Toast.LENGTH_SHORT).show();
            }else{
                int edad = calcularEdad(fechaSelec);
                String message = "Hola!"+ " " + "Mi nombre es:"+" " +binding.txtNombre.getText().toString() + " "+binding.txtApellido.getText().toString()
                        + "\n"
                        + "\n"
                        + "Mi genero es" + " " + selectedGenero
                        + "\n"
                        + "\n"
                        + "Mi fecha de nacimiento es: " + "\n" + binding.etPlannedDate.getText().toString()
                        + "\n"
                        + "\n"
                        + "Tengo" + " " + edad + " "+"años."
                        + "\n"
                        + "\n"
                        + secondMessage;
                ;
                intent.putExtra("value", message);
                startActivity(intent);
            }

        });

        binding.btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtNombre.setText("");
                binding.txtApellido.setText("");
                binding.etPlannedDate.setText("");
                binding.spinner.setSelection(0);
                binding.radioGroup.check(binding.rdbSi.getId());
                binding.cbxJava.setChecked(false);
                binding.cbxGo.setChecked(false);
                binding.cbxCsharp.setChecked(false);
                binding.cbxPython.setChecked(false);
                binding.cbxCpp.setChecked(false);
                binding.cbxJs.setChecked(false);
            }
        });
    }

    private int calcularEdad(Calendar fechaNac) {
        Calendar today = Calendar.getInstance();
        int edad = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)) {
            edad--;
        } else if (today.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) &&
                today.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH)) {
            edad--;
        }

        return edad;
    }



}