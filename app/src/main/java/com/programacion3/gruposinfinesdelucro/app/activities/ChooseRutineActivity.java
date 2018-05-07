package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.RutinasAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Enums;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;

public class ChooseRutineActivity extends NavigationActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Routine> arrayList = new ArrayList<Routine>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_created_routines);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.routines_recycler);


        adapter = new RutinasAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("exercises");
        Exercise barbell_bicep_drag_curl_exe = new Exercise("Barbell Bicep Drag Curl",
                "Bicep",
                "To begin this exercise; take a barbell with " +
                        "palms facing forward with the barbell resting at your pelvis. Then take the " +
                        "barbell and curl it towards your upper chest as in a way to “Drag” the bar up. " +
                        "Hold and squeeze the biceps tightly.",
                "http://www.directlyfitness.com/wordpress/wp-content/uploads/2012/05/Barbell-Drag-Curls.jpg",
                Enums.Exercisetype.REPEATED);
        ref.child(barbell_bicep_drag_curl_exe.getName()).setValue(barbell_bicep_drag_curl_exe);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("ChooseRutineActivity", snapshot.getValue(Exercise.class).toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ChooseRutineActivity", "Error retreiving data");
            }
        });

        //Prueba de la database

        DatabaseReference usersRef = database.getReference("users");

        Exercise air_bike_exe = new Exercise("Air Bike",
                "abs",
                "Start off lying flat as if you were going to perform a sit up putting your hands behind " +
                        "your your head and lifting your shoulders into a crunch position. Bring your knees up so " +
                        "that they are perpendicular with the floor and your lower legs are parallel with the ground " +
                        "as this will be your starting position. Slowly go through a cycle pedal motion kicking forward " +
                        "with your right leg and bringing in the knee of the left leg.",
                "http://www.directlyfitness.com/wordpress/wp-content/uploads/2012/05/Barbell-Drag-Curls.jpg",
                Enums.Exercisetype.REPEATED);

        ScheduledExercise exe1 = new ScheduledExercise(barbell_bicep_drag_curl_exe, 13);
        ScheduledExercise exe2 = new ScheduledExercise(air_bike_exe, 20);

        Routine routine = new Routine("Primera Semana");
        routine.addExercise(Enums.Day.TUESDAY, exe1);
        routine.addExercise(Enums.Day.THURSDAY, exe2);
        routine.addExercise(Enums.Day.SATURDAY, exe1);

        usersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("routines").setValue(routine);


    }


}
