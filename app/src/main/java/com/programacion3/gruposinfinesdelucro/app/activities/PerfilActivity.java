package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.programacion3.gruposinfinesdelucro.app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends NavigationActivity {
    boolean edit = false;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private Button uplButton;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    private CircleImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);
        EditText editNombre, editMail, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        editMail = findViewById(R.id.pesol_perfil);
        editMetas = findViewById(R.id.tiempo_perfil);
        updateInformation(editNombre, editMail, editMetas);
        editNombre.setEnabled(false);
        editMail.setEnabled(false);
        editMetas.setEnabled(false);

        uplButton = findViewById(R.id.subirFotoButton);
        image = findViewById(R.id.circle_profile_image);
        mStorage = FirebaseStorage.getInstance().getReference();



        uplButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT&&resultCode== RESULT_OK){
            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("fotosDePerfil").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri descargarFoto = taskSnapshot.getDownloadUrl();
                    Glide.with(PerfilActivity.this)
                            .load(descargarFoto).fitCenter().centerCrop().into(image);


                    Toast.makeText(PerfilActivity.this,"Se subió la foto",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void onEditClick(View view) {
        Button editButton;
        editButton = findViewById(R.id.editButton);
        //TODO
        EditText editNombre, editEdad, editMetas;
        editNombre = findViewById(R.id.nombrePerfil);
        //editEdad = findViewById(R.id.años_perfil);
        editMetas = findViewById(R.id.tiempo_perfil);
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
