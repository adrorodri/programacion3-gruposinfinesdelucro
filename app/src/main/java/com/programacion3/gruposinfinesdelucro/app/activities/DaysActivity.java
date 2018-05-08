package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;

public class DaysActivity extends NavigationActivity {
    Button lunes, martes, miercoles, jueves, viernes, sabado, domingo;

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
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        String nombreRutina = (String) b.get("name");
        lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaysActivity.this, RutinasActivity.class);
                startActivity(i);
            }
        });
        martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        domingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
