package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.EjerciciosAdapter;
import com.programacion3.gruposinfinesdelucro.app.adapters.ScheduledExerciseAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Enums;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;

public class RoutineExerciseActivity extends NavigationActivity {
    RecyclerView recyclerView;
    ScheduledExerciseAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Exercise> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rutinas);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.recycler);
        adapter = new ScheduledExerciseAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        Routine routine = (Routine) intent.getSerializableExtra("routine");
        int day = intent.getIntExtra("day", -1);

        ArrayList<ScheduledExercise> exercises = routine.getDaysList().get(day);

        adapter.setListContent(exercises);
        recyclerView.setAdapter(adapter);

    }


}



