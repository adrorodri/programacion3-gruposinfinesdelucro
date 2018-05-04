package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class PerfilActivity extends NavigationActivity {
    boolean edit = false;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);
        EditText editNombre, editMail, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editMail = findViewById(R.id.email_perfil);
        editMetas = findViewById(R.id.metas_perfil);
        updateInformation(editNombre, editMail, editMetas);
        editNombre.setEnabled(false);
        editMail.setEnabled(false);
        editMetas.setEnabled(false);

    }

    public void onEditClick(View view) {
        Button editButton;
        editButton = findViewById(R.id.editButton);
        EditText editNombre, editEdad, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        //editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.metas_perfil);
        if (!edit) {
            editNombre.setEnabled(true);
            //editEdad.setEnabled(true);
            editMetas.setEnabled(true);
            editButton.setText("Listo");
            edit = true;
        } else {
            editNombre.setEnabled(false);
            //editEdad.setEnabled(false);
            editMetas.setEnabled(false);
            editButton.setText("Edit");
            updateUser(editNombre);
            //updateInformation(editNombre);
            edit = false;
        }
    }

    private void updateUser(EditText nombre) {
        //TODO edad y metas
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nombre.getText().toString())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("PerfilActivity", "User profile updated.");
                            PerfilActivity.super.updateHeader();
                        } else {
                            Log.d("PerfilActivity", "Couldn't update info");
                        }
                    }
                });
    }


    public void updateInformation(EditText editNombre, EditText editMail, EditText editMetas) {
        editNombre.setText(user.getDisplayName());
        //String years = String.valueOf(user.getEdad());
        //ditEdad.setText(years);
        //editMetas.setText(user.getMetas());
        editMail.setText(user.getEmail());

    }


}
