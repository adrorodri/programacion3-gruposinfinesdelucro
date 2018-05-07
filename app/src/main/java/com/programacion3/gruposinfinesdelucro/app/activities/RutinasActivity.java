package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.EjerciciosAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

import java.util.ArrayList;

public class RutinasActivity extends NavigationActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Exercise> arrayList = new ArrayList<Exercise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rutinas);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.recycler);

        adapter = new EjerciciosAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
