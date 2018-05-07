package com.programacion3.gruposinfinesdelucro.app.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.EjerciciosAdapter;
import com.programacion3.gruposinfinesdelucro.app.classes.Enums;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

import java.util.ArrayList;

public class RutinasActivity extends NavigationActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Exercise> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rutinas);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.recycler);

        Exercise ejercicio = new Exercise("squat", "gluteo", "hola", "https://estaticos.muyinteresante.es/media/cache/760x570_thumb/uploads/images/pyr/55520750c0ea197b3fd51098/cuac-pato-p.jpg", Enums.Exercisetype.REPEATED);

        arrayList.add(ejercicio);

        adapter = new EjerciciosAdapter(arrayList, this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
