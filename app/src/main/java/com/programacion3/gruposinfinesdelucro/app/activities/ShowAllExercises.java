package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.EjerciciosAdapter;
import com.programacion3.gruposinfinesdelucro.app.adapters.ShowExerciseAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

import java.util.ArrayList;

public class ShowAllExercises extends NavigationActivity {
    private ArrayList<Exercise> exerciseArrayList= new ArrayList<Exercise>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show_all_exercises);
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        adapter = new ShowExerciseAdapter(exerciseArrayList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDatabase = database.getReference("exercises");


        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                exerciseArrayList.removeAll(exerciseArrayList);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Exercise exercise = snapshot.getValue(Exercise.class);
                    exerciseArrayList.add(exercise);
                    Log.d("exercise", exercise.getDescription());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowAllExercises.this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
            }
        });

        
    }
}
