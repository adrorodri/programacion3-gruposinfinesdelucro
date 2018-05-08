package com.programacion3.gruposinfinesdelucro.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.activities.DaysActivity;
import com.programacion3.gruposinfinesdelucro.app.activities.ExplainActivity;
import com.programacion3.gruposinfinesdelucro.app.activities.SetDurationActivity;
import com.programacion3.gruposinfinesdelucro.app.activities.SetRepsSetsActivity;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

import java.util.ArrayList;

public class ShowExerciseAdapter extends RecyclerView.Adapter<ShowExerciseAdapter.RecyclerViewHold>{
    private ArrayList<Exercise> exercises= new ArrayList<Exercise>();
    private Context context;

    public ShowExerciseAdapter(ArrayList<Exercise> exercises ,Context context) {
        this.exercises = exercises;
        this.context=context;

    }

    @Override
    public ShowExerciseAdapter.RecyclerViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_show_exercises, parent, false);
        return new ShowExerciseAdapter.RecyclerViewHold(view);
    }

    @Override
    public void onBindViewHolder(ShowExerciseAdapter.RecyclerViewHold holder, int position) {
        final Exercise exercise=exercises.get(position);
        holder.nombre_ejercicio.setText(exercise.getName());
        holder.tipo_ejercicio.setText(exercise.getType().toString());

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(exercise.getType().toString().equals("REPEATED")){
                    intent = new Intent(context, SetRepsSetsActivity.class);
                    intent.putExtra("Ejercicio", exercise.getType().toString());
                    context.startActivity(intent);
                }else {
                    intent = new Intent(context, SetDurationActivity.class);
                    intent.putExtra("Ejercicio", exercise.getType().toString());
                    context.startActivity(intent);
                }
            }
        });
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ExplainActivity.class);
                intent.putExtra("Ejercicio",exercise);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
    public static class RecyclerViewHold extends RecyclerView.ViewHolder {

        View itemView;
        TextView nombre_ejercicio, tipo_ejercicio;
        Button add,info;

        public RecyclerViewHold(View view) {
            super(view);
            itemView = view;
            // imageView = view.findViewById(R.id.img);
            nombre_ejercicio = view.findViewById(R.id.nombre_ejercicio);
            tipo_ejercicio = view.findViewById(R.id.tipo_ejercicio);
            add= view.findViewById(R.id.add);
            info= view.findViewById(R.id.info);
        }
    }
}
