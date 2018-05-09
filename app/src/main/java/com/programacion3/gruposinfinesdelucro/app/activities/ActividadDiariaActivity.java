package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.ScheduledExerciseAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Joaco99 on 20/04/2018.
 */

public class ActividadDiariaActivity extends NavigationActivity {
    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private ScheduledExerciseAdapter adapter;
    private String TAG = ActividadDiariaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_actividad_diaria);
        super.onCreate(savedInstanceState);

        //initialize attributes
        button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateRoutineActivity();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScheduledExerciseAdapter(this);

        //get current day
        Calendar calendar = Calendar.getInstance();
        int calendar_day = calendar.get(Calendar.DAY_OF_WEEK);
        final int day = fromDaytoIndex(calendar_day);

        Log.d(TAG, "Estas en la actividad diaria");
        loadData(day);
    }

    private void loadData(final int day) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = database.getReference("users").child(user.getUid()).child("routines");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Se encontró la lista");
                ArrayList<ScheduledExercise> list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataSnapshot child = snapshot.child("daysList").child(day + "");
                    for (DataSnapshot data : child.getChildren()) {
                        ScheduledExercise exe = data.getValue(ScheduledExercise.class);
                        list.add(exe);
                    }
                }

                Log.d(TAG, "Lista vacia: " + list.isEmpty());
                if (!list.isEmpty()) {
                    fillRecycler(list);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "No se pudo encontrar la lista");
            }
        });
    }

    private void fillRecycler(ArrayList<ScheduledExercise> list) {
        adapter.setListContent(list);
        recyclerView.setAdapter(adapter);
    }

    private void startCreateRoutineActivity() {
        Intent intent = new Intent(ActividadDiariaActivity.this, CreateRoutine.class);
        startActivity(intent);
    }

    private int fromDaytoIndex(int day) {
        if (day == Calendar.MONDAY) {
            return 0;
        } else if (day == Calendar.TUESDAY) {
            return 1;
        } else if (day == Calendar.WEDNESDAY) {
            return 2;
        } else if (day == Calendar.THURSDAY) {
            return 3;
        } else if (day == Calendar.FRIDAY) {
            return 4;
        } else if (day == Calendar.SATURDAY) {
            return 5;
        } else if (day == Calendar.SUNDAY) {
            return 6;
        } else {
            throw new IllegalStateException();
        }
    }
}
