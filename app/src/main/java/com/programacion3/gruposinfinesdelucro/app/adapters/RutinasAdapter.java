package com.programacion3.gruposinfinesdelucro.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.activities.DaysActivity;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;

import java.util.ArrayList;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RecyclerViewHolder> {
    private ArrayList<Routine> arrayList = new ArrayList<Routine>();
    private Context context;

    public RutinasAdapter(ArrayList<Routine> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RutinasAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routines_preview, parent, false);
        return new RutinasAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RutinasAdapter.RecyclerViewHolder holder, int position) {
        final Routine rutina = arrayList.get(position);
        holder.nombre.setText(rutina.getName());
        holder.dificultad.setText(rutina.getDificultad());
        if (rutina.getDificultad() == "hard") {
            holder.dificultad.setTextColor(Color.parseColor("#CCFF1E1E"));
        } else if (rutina.getDificultad() == "medium") {
            holder.dificultad.setTextColor(Color.parseColor("#CCFFB20C"));
        } else {
            holder.dificultad.setTextColor(Color.parseColor("#CC69FF0C"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DaysActivity.class);
                intent.putExtra("name", rutina.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView nombre, creador, dificultad;

        RecyclerViewHolder(View view) {
            super(view);
            itemView = view;
            nombre = (TextView) view.findViewById(R.id.rutina);
            dificultad = (TextView) view.findViewById(R.id.dificultad);

        }
    }
}
