package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Routines;
import com.programacion3.gruposinfinesdelucro.app.adapters.RutinasAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChooseRutineActivity extends NavigationActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] exercises, duration;
    int[] img_res;
    ArrayList<Routines> arrayList = new ArrayList<Routines>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_created_routines);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.routines_recycler);
        List<Ejercicio> list = new LinkedList<>();
        Ejercicio ejercicio = new Ejercicio(R.drawable.bicep1, "Curl", "12 min");
        Ejercicio ejercicio1 = new Ejercicio(R.drawable.chest1, "press", "12 reps");
        Ejercicio ejercicio2 = new Ejercicio(R.drawable.cardio1, "legs", "12 min");
        list.add(ejercicio);
        list.add(ejercicio1);
        list.add(ejercicio2);
        Routines routine1 = new Routines("Ripped body","Michel","hard",list);
        arrayList.add(routine1);
        adapter = new RutinasAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
