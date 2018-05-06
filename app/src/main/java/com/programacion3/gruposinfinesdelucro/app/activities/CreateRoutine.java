package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;

public class CreateRoutine extends NavigationActivity {
    Button crear,escoger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_create_routine);
        super.onCreate(savedInstanceState);
        crear= findViewById(R.id.crearlarutina);
        escoger= findViewById(R.id.escogerlarutina);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(CreateRoutine.this,ShowAllExercises.class );
                startActivity(i);
            }
        });
        escoger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

}
