package com.programacion3.gruposinfinesdelucro.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.activities.EjercicioActivity;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.RecyclerViewHolder> {
    private ArrayList<Exercise> arrayList = new ArrayList<Exercise>();
    private Context context;

    public EjerciciosAdapter(ArrayList<Exercise> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_exercises, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final Exercise ejercicio = arrayList.get(position);
        holder.exercise.setText(ejercicio.getName());
        holder.duration.setText(ejercicio.getMusculo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EjercicioActivity.class);
                intent.putExtra("Ejercicio", (Parcelable) ejercicio);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView imageView;
        TextView exercise, duration;

        public RecyclerViewHolder(View view) {
            super(view);
            itemView = view;
            imageView = view.findViewById(R.id.img);
            exercise = view.findViewById(R.id.name);
            duration = view.findViewById(R.id.duration);

        }
    }

}
