package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;

/**
 * Created by Joaco99 on 20/04/2018.
 */

public class ActividadDiariaActivity extends NavigationActivity {
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_actividad_diaria);
        super.onCreate(savedInstanceState);
        add = findViewById(R.id.addrutina);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ActividadDiariaActivity.this,CreateRoutine.class );
                startActivity(intent);
            }
        });
    }
}
