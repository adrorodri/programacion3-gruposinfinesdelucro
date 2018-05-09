package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.RoutinesAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;

public class RoutinesActivity extends NavigationActivity {
    private RecyclerView recyclerView;
    private RoutinesAdapter adapter;
    private String TAG = RoutinesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_routines);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.routines_recycler);
        adapter = new RoutinesAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = database.getReference("users").child(user.getUid()).child("routines");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Se encontró la lista");
                ArrayList<Routine> list = new ArrayList<>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String difficulty = (String) snapshot.child("difficulty").getValue();
                    String name = (String) snapshot.child("name").getValue();
                    Routine routine = new Routine(name, difficulty);
                    routine.resetDaysList();
                    for(DataSnapshot day : snapshot.child("daysList").getChildren()){
                        int dayIndex = Integer.valueOf(day.getKey());
                        for(DataSnapshot exe : day.getChildren()){
                            ScheduledExercise exercise = exe.getValue(ScheduledExercise.class);
                            routine.addExercise(dayIndex, exercise);
                        }
                    }

                    list.add(routine);
                }
                fillRecycler(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "No se pudo encontrar la lista");
            }
        });
    }


    private void fillRecycler(ArrayList<Routine> routines){
        adapter.setListContent(routines);
        recyclerView.setAdapter(adapter);
    }

}
