package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;

public class DaysActivity extends NavigationActivity {
    private Button lunes, martes, miercoles, jueves, viernes, sabado, domingo;
    private Routine routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_days);
        super.onCreate(savedInstanceState);
        lunes = findViewById(R.id.lunes);
        martes = findViewById(R.id.martes);
        miercoles = findViewById(R.id.miercoles);
        jueves = findViewById(R.id.jueves);
        viernes = findViewById(R.id.viernes);
        sabado = findViewById(R.id.sabado);
        domingo = findViewById(R.id.domingo);

        Intent intent = getIntent();
        routine = (Routine)intent.getSerializableExtra("routine");
        lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(0);
            }
        });
        martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(1);
            }
        });
        miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(2);
            }
        });
        jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(3);
            }
        });
        viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(4);
            }
        });
        sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(5);
            }
        });
        domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDayExercises(6);
            }
        });

    }

    public void showDayExercises(int day){
        Intent intent = new Intent(this, RoutineExerciseActivity.class);
        intent.putExtra("day", day);
        intent.putExtra("routine", routine);
        startActivity(intent);
    }
}
