package com.example.tarea_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StudenViewHolder> {

    private final List<Tarea> students;

    public RecyclerAdapter(List<Tarea> students) {
        this.students = students;
    }

    @NonNull
    @NotNull
    @Override
    public StudenViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new RecyclerAdapter.StudenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudenViewHolder holder, int position) {
        Tarea student = students.get(position);

        holder.tarea.setText(student.getTarea());
        //holder.id.setText(student.getTarea());
        holder.imageView.setImageResource(student.getImageResId());


        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Eliminar tarea")
                        .setMessage("Confirmar eliminar tarea")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                students.remove(currentPosition);
                                notifyItemRemoved(currentPosition);
                                notifyItemRangeChanged(currentPosition, students.size());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View dialogView = inflater.inflate(R.layout.dialog_modificar, null);
                EditText editTarea = dialogView.findViewById(R.id.editTarea);

                editTarea.setText(student.getTarea());

                builder.setView(dialogView)
                        .setTitle("Modificar Tarea")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Tarea updatedTarea = new Tarea(editTarea.getText().toString(), student.getImageResId());
                                students.set(currentPosition, updatedTarea);
                                notifyItemChanged(currentPosition);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudenViewHolder extends RecyclerView.ViewHolder {

        TextView tarea;
        Button borrar;
        ImageView imageView;
        Button modificar;

        public StudenViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tarea = itemView.findViewById(R.id.tarea);
            borrar = itemView.findViewById(R.id.btnBorrar);
            imageView = itemView.findViewById(R.id.imageView2);
            modificar = itemView.findViewById(R.id.btnModificar);
        }
    }

}