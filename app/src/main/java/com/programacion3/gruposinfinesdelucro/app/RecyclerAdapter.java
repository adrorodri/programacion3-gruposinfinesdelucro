package com.programacion3.gruposinfinesdelucro.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<DataProvider> arrayList=new ArrayList<>();
    public RecyclerAdapter(ArrayList<DataProvider> arrayList){
        this.arrayList=arrayList;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.preview_exercises,parent,false);
        RecyclerViewHolder recyclerViewHolder= new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataProvider dataProvider= arrayList.get(position);
        holder.imageView.setImageResource(dataProvider.getImg_res());
        holder.exercise.setText(dataProvider.getExercise());
        holder.duration.setText(dataProvider.getDuration());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView exercise,duration;
        public RecyclerViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.img);
            exercise = (TextView) view.findViewById(R.id.exercise);
            duration = (TextView) view.findViewById(R.id.duration);

        }
    }
}
