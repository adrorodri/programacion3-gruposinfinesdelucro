package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;

public class ShowAllExercises extends NavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show_all_exercises);
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("exercises");
    }
}
