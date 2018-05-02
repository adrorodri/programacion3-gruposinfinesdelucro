package com.programacion3.gruposinfinesdelucro.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilActivity extends NavigationActivity {
    boolean edit = false;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);
        EditText editNombre, editEdad, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.metas_perfil);
        updateInformation(editNombre, editEdad, editMetas);
        editNombre.setEnabled(false);
        editEdad.setEnabled(false);
        editMetas.setEnabled(false);

    }

    public void onEditClick(View view) {
        Button editButton;
        editButton = findViewById(R.id.editButton);
        EditText editNombre, editEdad, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.metas_perfil);
        if (!edit) {
            editNombre.setEnabled(true);
            editEdad.setEnabled(true);
            editMetas.setEnabled(true);
            editButton.setText("Listo");
            edit = true;
        } else {
            editNombre.setEnabled(false);
            editEdad.setEnabled(false);
            editMetas.setEnabled(false);
            editButton.setText("Edit");
            updateInformation(editNombre, editEdad, editMetas);
            edit = false;
        }
    }

    public void updateInformation(EditText editNombre, EditText editEdad, EditText editMetas) {
        editNombre.setText(user.getName());
        editEdad.setText(user.getEdad());
        editMetas.setText(user.getMetas());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
