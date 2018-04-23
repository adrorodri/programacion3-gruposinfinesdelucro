package com.programacion3.gruposinfinesdelucro.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ExplainActivity extends NavigationActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_explain);
        super.onCreate(savedInstanceState);
        imageView = findViewById(R.id.ejercicio);
        Bundle parametros = this.getIntent().getExtras();

    }
}
