package com.programacion3.gruposinfinesdelucro.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<Ejercicio> arrayList = new ArrayList<Ejercicio>();
    private Context context;

    RecyclerAdapter(ArrayList<Ejercicio> arrayList, Context context) {
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
        final Ejercicio ejercicio = arrayList.get(position);
        holder.imageView.setImageResource(ejercicio.getImg_type());
        holder.exercise.setText(ejercicio.getName());
        holder.duration.setText(ejercicio.getDuration());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EjercicioActivity.class);
                intent.putExtra("Ejercicio", ejercicio);
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
            imageView = (ImageView) view.findViewById(R.id.img);
            exercise = (TextView) view.findViewById(R.id.name);
            duration = (TextView) view.findViewById(R.id.duration);

        }
    }

}
