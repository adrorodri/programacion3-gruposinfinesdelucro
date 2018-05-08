package com.programacion3.gruposinfinesdelucro.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;

/**
 * Created by Joaco99 on 07/05/2018.
 */

public class ScheduledExerciseAdapter extends  RecyclerView.Adapter<ScheduledExerciseAdapter.ScheduledExerciseHolder>{
    private ArrayList<ScheduledExercise> exercises = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public ScheduledExerciseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ScheduledExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.scheduledexercise_preview, parent, false);
        return new ScheduledExerciseHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduledExerciseHolder holder, int position) {
        ScheduledExercise scheduledExercise = exercises.get(position);
        holder.name .setText(scheduledExercise.getExercise().getName());
        String content;
        if(scheduledExercise.getSec_duration() == -1){
            content = scheduledExercise.getSec_duration() + " secs";
        }else{
            content = scheduledExercise.getSeries() + " series, " + scheduledExercise.getRepetitions() + " rep.";
        }
        holder.schedule.setText(content);

        Glide.with(this.context).load(scheduledExercise.getExercise().getImagen()).into(holder.picture);

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setListContent(ArrayList<ScheduledExercise> exercises){
        this.exercises = exercises;
        notifyItemRangeChanged(0, exercises.size());
    }

    class ScheduledExerciseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name, schedule;
        private ImageView picture;

        ScheduledExerciseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.nameScheduledExercise);
            schedule = itemView.findViewById(R.id.scheduleScheduledExercise);
            picture = itemView.findViewById(R.id.pictureScheduledExercise);
        }

        @Override
        public void onClick(View view) {

        }
    }
}



