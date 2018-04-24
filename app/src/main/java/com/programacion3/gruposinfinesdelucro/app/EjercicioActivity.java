package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EjercicioActivity extends NavigationActivity {

    Ejercicio eje;

    ImageView exerciseImage;
    TextView exerciseNameTextView, seriesNumberTextView, repetitionsNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ejercicio);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        eje = (Ejercicio) intent.getSerializableExtra("Ejercicio");

        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseNameTextView = findViewById(R.id.exerciseNameTextView);
        seriesNumberTextView = findViewById(R.id.seriesNumberTextView);
        repetitionsNumberTextView = findViewById(R.id.repetitionsNumberTextView);

        exerciseImage.setImageResource(R.drawable.prueba);
        exerciseNameTextView.setText(eje.getName());
        seriesNumberTextView.setText("20");
        repetitionsNumberTextView.setText("5");
    }
}
