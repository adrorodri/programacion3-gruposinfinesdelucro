package com.programacion3.gruposinfinesdelucro.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RutinasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] exercises,duration,img_res;

    ArrayList<DataProvider> arrayList;

    {
        arrayList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        /*
        exercises = getResources().getStringArray(R.id);
        duration = getResources().getStringArray(R.id);

        int i=0;
        for (String exercise: exercises){
            DataProvider dataProvider=new DataProvider("bicep1","curl","4 sets de 14");
            arrayList.add(dataProvider);
            i++;
        }
        */
        DataProvider dataProvider=new DataProvider(1,"curl","4 sets de 14");
        DataProvider dataProvider2=new DataProvider(2,"curl","4 sets de 14");
        arrayList.add(dataProvider);
        arrayList.add(dataProvider2);
        adapter= new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
