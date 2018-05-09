package com.programacion3.gruposinfinesdelucro.app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.RoutineHolder> {
    private ArrayList<Routine> routines = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public RoutinesAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RoutineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.routines_preview, parent, false);
        return new RoutineHolder(view);
    }

    @Override
    public void onBindViewHolder(RoutineHolder holder, int position) {
        final Routine routine = routines.get(position);

        holder.name.setText(routine.getName());
        holder.difficulty.setText(routine.getDifficulty());

        int color = getDifficultyColor(routine.getDifficulty());
        holder.difficulty.setTextColor(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void setListContent(ArrayList<Routine> routines){
        this.routines = routines;
        notifyItemRangeChanged(0, routines.size());
    }

    private int getDifficultyColor(String difficulty){
        if(difficulty.equals("EASY")){
            return Color.GREEN;
        }else if(difficulty.equals("MEDIUM")){
            return Color.YELLOW;
        }else if(difficulty.equals("HARD")){
            return  Color.RED;
        }else{
            return Color.BLUE;
        }
    }

    static class RoutineHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView name, difficulty;

        RoutineHolder(View view) {
            super(view);
            itemView = view;
            name = view.findViewById(R.id.nameRoutine);
            difficulty = view.findViewById(R.id.difficultyRoutine);
        }
    }
}
