package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;

public class SetRepsSetsActivity extends NavigationActivity {
    private DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    private Button done;
    private EditText reps,sets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setrepset);
        super.onCreate(savedInstanceState);
        done = (Button) findViewById(R.id.done);
        reps= (EditText) findViewById(R.id.set_repeticiones);
        sets= (EditText) findViewById(R.id.set_sets);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int repsIngresado = Integer.parseInt(reps.getText().toString());
                repsIngresado = ((reps.getText().toString() != null) && !reps.getText().toString().equals("")) ? Integer.parseInt(reps.getText().toString()) : 20;
                int setsIngresado = Integer.parseInt(sets.getText().toString());
                if ((sets.getText().toString() != null) && !sets.getText().toString().equals("")) {
                    setsIngresado = Integer.parseInt(sets.getText().toString());
                }else {
                    setsIngresado = 4;
                }
            }
        });
    }

    public void pasar_dia(View view) {
    }
}
