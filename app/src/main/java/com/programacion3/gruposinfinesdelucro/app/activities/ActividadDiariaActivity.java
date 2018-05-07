package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.EjerciciosAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Joaco99 on 20/04/2018.
 */

public class ActividadDiariaActivity extends NavigationActivity {
    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Ejercicio> arrayList = new ArrayList<Ejercicio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_actividad_diaria);
        super.onCreate(savedInstanceState);
        button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateRoutineActivity();
            }
        });

        Calendar calendar = Calendar.getInstance();
        Log.d("ActividadDiariaActivity", "day: " + calendar.get(Calendar.DAY_OF_WEEK));
        Log.d("ActividadDiariaActivity", "time: " + calendar.getTime());
        Log.d("ActividadDiariaActivity", "time: " + calendar.getTimeZone().getDisplayName());

        recyclerView = findViewById(R.id.recycler);
    }

    private void startCreateRoutineActivity(){
        Intent intent= new Intent(ActividadDiariaActivity.this,CreateRoutine.class );
        startActivity(intent);
    }

    private int fromDaytoIndex(int day){
        if(day == Calendar.MONDAY){
            return 0;
        }else if(day == Calendar.TUESDAY){
            return 1;
        }else if(day == Calendar.WEDNESDAY){
            return 2;
        }else if(day == Calendar.THURSDAY){
            return 3;
        }else if(day == Calendar.FRIDAY){
            return 4;
        }else if(day == Calendar.SATURDAY){
            return 5;
        }else if(day == Calendar.SUNDAY){
            return 6;
        }else{
            throw new IllegalStateException();
        }
    }
}
