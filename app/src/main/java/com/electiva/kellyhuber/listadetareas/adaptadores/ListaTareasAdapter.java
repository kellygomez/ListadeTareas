package com.electiva.kellyhuber.listadetareas.adaptadores;

import android.graphics.Paint;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electiva.kellyhuber.listadetareas.Modelos.Tarea;
import com.electiva.kellyhuber.listadetareas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.electiva.kellyhuber.listadetareas.R.id.chkTarea;

/**
 * Created by kelly on 23/10/2017.
 */

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.ListaViewHolder> {

    OnListenerItemCheck onListenerItemCheck;
    List<Tarea> dataset = new ArrayList<>();

    public ListaTareasAdapter(List<Tarea> dataset, OnListenerItemCheck onListenerItemCheck) {
        super();
        this.dataset = dataset;
        this.onListenerItemCheck = onListenerItemCheck;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View intemTareaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea,parent,false);
        return new ListaViewHolder(intemTareaView);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        Tarea tarea = dataset.get(position);

        if(tarea.isRealizada()){
            holder.descTarea.setPaintFlags(holder.descTarea.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.descTarea.setPaintFlags(holder.descTarea.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.descTarea.setText(tarea.getDescripcion());
        holder.fecha.setText(tarea.getFecha());
        holder.checked.setChecked(tarea.isRealizada());


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ListaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTarea)
        TextView descTarea;

        @BindView(R.id.txtFecha)
        TextView fecha;

        @BindView(chkTarea)
        AppCompatCheckBox checked;


        public ListaViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            if (onListenerItemCheck != null) {

                checked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onListenerItemCheck.itemCambioEstado(getAdapterPosition(),
                                checked.isChecked());
                    }
                });
            }

        }


    }

    public interface OnListenerItemCheck {

        void itemCambioEstado(int posicion, boolean realizada);

    }
}
