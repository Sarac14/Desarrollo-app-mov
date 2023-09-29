package com.example.tarea_2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Tarea> {
    public ListAdapter(@NonNull Context context, @NonNull List<Tarea> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        Tarea item = getItem(position);

        //TextView name = convertView.findViewById(R.id.tarea);
        TextView tarea = convertView.findViewById(R.id.tarea);

       // name.setText(item.getName());
        tarea.setText(item.getTarea());

        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Eliminar tarea")
                    .setMessage("Confirmar eliminar tarea")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Tarea usuario = getItem(position);
                            remove(usuario);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        });

        Button btnModificar = convertView.findViewById(R.id.btnEditarList);
        btnModificar.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View dialogView = inflater.inflate(R.layout.dialog_modificar, null);
            EditText editTarea = dialogView.findViewById(R.id.editTarea);

            Tarea currentTarea = getItem(position);
            editTarea.setText(currentTarea.getTarea());

            builder.setView(dialogView)
                    .setTitle("Modificar Tarea")
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        Tarea updatedTarea = new Tarea(editTarea.getText().toString(), currentTarea.getImageResId());
                        remove(currentTarea);
                        insert(updatedTarea, position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });


        return convertView;
    }
}