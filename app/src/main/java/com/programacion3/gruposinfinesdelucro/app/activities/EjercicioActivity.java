package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

public class EjercicioActivity extends NavigationActivity {

    Exercise eje;

    ImageView exerciseImage;
    TextView exerciseNameTextView, seriesNumberTextView, repetitionsNumberTextView;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ejercicio);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        eje = (Exercise) intent.getSerializableExtra("Ejercicio");
        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseNameTextView = findViewById(R.id.exerciseNameTextView);
        seriesNumberTextView = findViewById(R.id.seriesNumberTextView);
        repetitionsNumberTextView = findViewById(R.id.repetitionsNumberTextView);

        exerciseImage.setImageResource(R.drawable.prueba);
        exerciseNameTextView.setText("wow");
        seriesNumberTextView.setText("4");
        repetitionsNumberTextView.setText("20");
        button = findViewById(R.id.info);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EjercicioActivity.this, ExplainActivity.class);
                intent.putExtra("Ejercicio", (Parcelable) eje);
                startActivity(intent);
            }
        });

        button2 = findViewById(R.id.ok);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EjercicioActivity.this, RutinasActivity.class);
                startActivity(intent);
            }
        });



    }

}
