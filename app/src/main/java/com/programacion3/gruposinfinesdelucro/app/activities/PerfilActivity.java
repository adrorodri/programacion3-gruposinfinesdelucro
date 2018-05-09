package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private static final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);
        TextView editNombre, editMail;
        editNombre = findViewById(R.id.nombrePerfil);
        editMail = findViewById(R.id.mail);

        ImageView editImage;
        editImage = findViewById(R.id.circle_profile_image);
        updateInformation(editNombre, editMail, editImage);


    }




    public void updateInformation(TextView editNombre, TextView editMail, ImageView editFoto) {
        editNombre.setText(user.getDisplayName());
        editMail.setText(user.getEmail());
        Glide.with(PerfilActivity.this)
                .load(user.getPhotoUrl()).fitCenter().centerCrop().into(editFoto);

    }
}
