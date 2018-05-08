package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;

public class CreateRoutine extends NavigationActivity {
    private Button createButton, chooseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_create_routine);
        super.onCreate(savedInstanceState);
        createButton = findViewById(R.id.crearlarutina);
        chooseButton = findViewById(R.id.escogerlarutina);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(CreateRoutine.this,ShowAllExercises.class );
                startActivity(i);
            }
        });



        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

}
