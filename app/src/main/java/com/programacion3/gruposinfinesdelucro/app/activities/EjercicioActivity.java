package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.ScheduledExercise;

public class EjercicioActivity extends NavigationActivity {

    private ScheduledExercise eje;

    ImageView exerciseImage;
    TextView exerciseNameTextView, seriesNumberTextView, repetitionsNumberTextView, seriesFieldTextView, repetitionsFieldTextView;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ejercicio);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        eje = (ScheduledExercise) intent.getSerializableExtra("Ejercicio");
        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseNameTextView = findViewById(R.id.exerciseNameTextView);
        seriesNumberTextView = findViewById(R.id.seriesNumberTextView);
        repetitionsNumberTextView = findViewById(R.id.repetitionsNumberTextView);

        seriesFieldTextView = findViewById(R.id.seriesFieldTextView);
        repetitionsFieldTextView = findViewById(R.id.repetitionsFieldTextView);


        if (eje.getSec_duration() != -1) {
            repetitionsNumberTextView.setVisibility(View.GONE);
            repetitionsFieldTextView.setVisibility(View.GONE);
            seriesFieldTextView.setText("Duration");
            seriesNumberTextView.setText(eje.getSec_duration() + " secs");
        } else {
            exerciseImage.setImageResource(R.drawable.prueba);
            exerciseNameTextView.setText(eje.getExercise().getName());
            seriesNumberTextView.setText(eje.getSeries() + "");
            repetitionsNumberTextView.setText(eje.getRepetitions() + "");

        }
        Glide.with(this).load(eje.getExercise().getImagen()).into(exerciseImage);

        button = findViewById(R.id.info);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EjercicioActivity.this, ExplainActivity.class);
                intent.putExtra("Ejercicio", eje.getExercise());
                startActivity(intent);
            }
        });

        button2 = findViewById(R.id.ok);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

}
