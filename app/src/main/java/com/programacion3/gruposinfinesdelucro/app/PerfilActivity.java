package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PerfilActivity extends NavigationActivity {
    boolean edit = false;
    User user = super.getUser();


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
            updateUser(editNombre.getText().toString(),editEdad.getText().toString(),editMetas.getText().toString());
            updateInformation(editNombre, editEdad, editMetas);
            edit = false;
        }
    }

    private void updateUser(String nombre, String edad, String metas) {
        user.setName(nombre);
        int edad2 = Integer.parseInt(edad);
        user.setEdad(edad2);
        user.setMetas(metas);
        super.setUser(this.user);
    }

    public void updateInformation(EditText editNombre, EditText editEdad, EditText editMetas) {
        editNombre.setText(user.getName());
        String years = String.valueOf(user.getEdad());
        editEdad.setText(years);
        editMetas.setText(user.getMetas());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
