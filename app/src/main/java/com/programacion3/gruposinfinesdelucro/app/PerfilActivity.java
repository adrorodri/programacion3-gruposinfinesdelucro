package com.programacion3.gruposinfinesdelucro.app;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class PerfilActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);
        EditText editNombre,editEdad,editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.metas_perfil);

        editNombre.setEnabled(false);
        editEdad.setEnabled(false);
        editMetas.setEnabled(false);
    }
    public void onEditClick(MenuItem item) {
        EditText editNombre,editEdad,editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.metas_perfil);

        editNombre.setEnabled(true);
        editEdad.setEnabled(true);
        editMetas.setEnabled(true);
    }
}
