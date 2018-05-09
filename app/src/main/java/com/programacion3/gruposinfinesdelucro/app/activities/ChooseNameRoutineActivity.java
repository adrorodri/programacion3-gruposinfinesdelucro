package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Routine;

public class ChooseNameRoutineActivity extends NavigationActivity{
    private EditText editText;
    private RadioGroup radioGroup;
    private Button button;
    private Routine routine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_routine_name);
        super.onCreate(savedInstanceState);
        editText=  findViewById(R.id.nombredelarutina);
        radioGroup= findViewById(R.id.dificul);
        button= findViewById(R.id.doneee);
        final String[] string = new String[1];
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    routine= new Routine(editText.getText().toString(),string[0]);
                    Intent intent= new Intent(ChooseNameRoutineActivity.this,ShowAllExercises.class);
                    intent.putExtra("rutina",routine);
                    startActivity(intent);

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.easy){
                    string[0] ="EASY";
                }else if (i==R.id.medium){
                    string[0] ="MEDIUM";
                }else{
                    string[0] ="HARD";
                }

            }
        });

    }
}
