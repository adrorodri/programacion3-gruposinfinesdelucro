package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.classes.Exercise;

public class ExplainActivity extends NavigationActivity {

    Exercise eje;

    TextView titulo, descripcion;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("exercises");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_explaintest);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        eje = (Exercise) intent.getSerializableExtra("Ejercicio");

        String nombre = eje.getName();
        String descrip = eje.getDescription();
        String img = eje.getImagen();

        descripcion = findViewById(R.id.explicacion);
        titulo = findViewById(R.id.titulo);

        ImageView imageView = findViewById(R.id.imgex);

        Glide.with(this).load(img).into(imageView);

        descripcion.setText(descrip);
        titulo.setText(nombre);



    }
}
