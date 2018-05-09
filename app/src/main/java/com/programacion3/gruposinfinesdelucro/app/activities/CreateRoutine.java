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
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateRoutine.this, ChooseNameRoutineActivity.class);
                startActivity(i);
            }
        });

    }

}
