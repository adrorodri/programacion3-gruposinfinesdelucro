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
    String[] exercises,duration;
    int[] img_res;
    ArrayList<DataProvider> arrayList= new ArrayList<DataProvider>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        /*
        exercises = getResources().getStringArray(R.id);
        duration = getResources().getStringArray(R.id);
        * */
        int i=0;
        for (String exercise: exercises){
            DataProvider dataProvider=new DataProvider(img_res[i],exercise,duration[i]);
            arrayList.add(dataProvider);
            i++;
        }
        adapter= new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
