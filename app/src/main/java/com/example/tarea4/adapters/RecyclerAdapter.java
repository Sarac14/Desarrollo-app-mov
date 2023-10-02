package com.example.tarea4.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;


import com.example.tarea4.R;
import com.example.tarea4.data.TareaViewModel;
import com.example.tarea4.entities.Tarea;



public class RecyclerAdapter extends ListAdapter<Tarea, RecyclerAdapter.TareaViewHolder> {

    //private final List<Tarea> students;
    private TareaViewModel mViewModel;

    public RecyclerAdapter(TareaViewModel viewModel, DiffUtil.ItemCallback<Tarea> diffCallback) {
        super(diffCallback);
        this.mViewModel = viewModel;
    }


    /*public RecyclerAdapter(List<Tarea> students) {
        this.students = students;
    }*/

    @NonNull
    @NotNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return TareaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TareaViewHolder holder, int position) {
        Tarea current = getItem(position);
        holder.bind(current.getTarea());

        holder.cbxDone.setOnCheckedChangeListener(null);

        if(current.isCompletada()){
            holder.tareaItemView.setPaintFlags(holder.tareaItemView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.cbxDone.setChecked(true);
        } else {
            holder.tareaItemView.setPaintFlags(holder.tareaItemView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.cbxDone.setChecked(false);
        }

        holder.cbxDone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            current.setCompletada(isChecked);
            mViewModel.update(current);

            if(isChecked){
                holder.tareaItemView.setPaintFlags(holder.tareaItemView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.tareaItemView.setPaintFlags(holder.tareaItemView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });


       holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // int currentPosition = holder.getAdapterPosition();

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Eliminar tarea")
                        .setMessage("Confirmar eliminar tarea")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mViewModel.eliminar(current);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

       holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = LayoutInflater.from(view.getContext());
                View dialogView = inflater.inflate(R.layout.dialog_modificar, null);
                EditText editTarea = dialogView.findViewById(R.id.editTarea);

                editTarea.setText(current.getTarea());

                builder.setView(dialogView)
                        .setTitle("Modificar Tarea")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String updatedTarea = editTarea.getText().toString();
                                current.setTarea(updatedTarea);
                                mViewModel.update(current);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
    }



    public static class TareaDiff extends DiffUtil.ItemCallback<Tarea> {

        @Override
        public boolean areItemsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getTarea().equals(newItem.getTarea()) &&
                    oldItem.isCompletada() == newItem.isCompletada();
        }
    }


    static class TareaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tareaItemView;
        private final CheckBox cbxDone;
        private final Button borrar;
        private final Button modificar;

        private TareaViewHolder(View itemView) {
            super(itemView);
            tareaItemView = itemView.findViewById(R.id.tarea);
            cbxDone = itemView.findViewById(R.id.cbxDone);
            borrar = itemView.findViewById(R.id.btnBorrar);
            modificar = itemView.findViewById(R.id.btnModificar);
        }

        public void bind(String text) {
            tareaItemView.setText(text);
        }

        static TareaViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycler, parent, false);
            return new TareaViewHolder(view);
        }

    }

}