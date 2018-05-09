package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.programacion3.gruposinfinesdelucro.app.R;

import java.io.Serializable;

/**
 * Created by Samuel on 08/05/2018.
 */

public class ChangeInfoActivity extends NavigationActivity implements Serializable {

    String password;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private static final int GALLERY_INTENT = 1;

    private AuthCredential credential;

    //Bundle bundle = getIntent().getStringExtr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_info);
        super.onCreate(savedInstanceState);
        dialog();

        EditText editName, editMail;
        TextView editPic, editPic2;
        editName = findViewById(R.id.edit_name2);
        editMail = findViewById(R.id.edit_mail2);
        editPic = findViewById(R.id.edit_prof_picture);
        editPic2 = findViewById(R.id.edit_prof_picture2);

        updateInfo(editName, editMail);



        editPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });
    }

    private void updateInfo(EditText editNombre, EditText editMail) {
        editNombre.setText(user.getDisplayName());
        editMail.setText(user.getEmail());
    }

    public void buttonClick(View view) {
        EditText editName, editMail;
        editName = findViewById(R.id.edit_name2);
        editMail = findViewById(R.id.edit_mail2);
        switch (view.getId()) {
            case R.id.edit_button_save: {
                saveChanges(editName, editMail);
                Intent intent = new Intent(ChangeInfoActivity.this,ActividadDiariaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.edit_password: {
                dialog2();

                break;
            }
            case R.id.edit_password2: {
                dialog2();

                break;
            }
            case R.id.delete_account: {
                deleteAccount();

                break;
            }
            case R.id.delete_account2: {
                deleteAccount();

                break;
            }
        }
    }



    public void saveChanges(EditText name, final EditText mail) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name.getText().toString())
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("PerfilActivity", "User profile updated.");
                            ChangeInfoActivity.super.updateHeader();
                        } else {
                            Log.d("PerfilActivity", "Couldn't update info");
                        }
                    }
                });


        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    user.updateEmail(mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangeInfoActivity.this, "Email updated", Toast.LENGTH_SHORT).show();
                                user.sendEmailVerification();
                                finish();
                            } else {
                                Toast.makeText(ChangeInfoActivity.this, task.getException() + "", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(ChangeInfoActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void updatePassword() {
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(ChangeInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ChangeInfoActivity.this, "Contraseña Actualizada", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView editImage;
        editImage = findViewById(R.id.circle_profile_image);
        if(requestCode==GALLERY_INTENT&&resultCode== RESULT_OK){
            Uri uri = data.getData();

            Toast.makeText(ChangeInfoActivity.this,"Se subió la foto",Toast.LENGTH_SHORT).show();

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(uri)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("PerfilActivity", "User profile updated.");
                                ChangeInfoActivity.super.updatePicHeader();
                                ChangeInfoActivity.super.updateHeader();
                            }
                        }
                    });

        }
    }
    public void dialog(){
        AlertDialog.Builder text = new AlertDialog.Builder(this);
        text.setTitle("Password");
        text.setMessage("Enter Password");
        final EditText editPass = new EditText(this);
        editPass.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPass.setSelection(editPass.getText().length());
        text.setView(editPass);
        text.setCancelable(false);

        text.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                final String password = editPass.getText().toString();
                if(!password.equals("")) {
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), password);
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                ChangeInfoActivity.this.credential = EmailAuthProvider.getCredential(user.getEmail(), password);
                                dialogInterface.dismiss();

                                ChangeInfoActivity.this.setPassword(password);
                            } else {
                                dialog();
                                Toast.makeText(ChangeInfoActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    dialog();
                }
            }
        });
        AlertDialog alertDialog = text.create();
        alertDialog.show();
    }


    public void deleteAccount(){
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("God bye", "User account deleted.");
                                            startFirstActivity();
                                        }
                                    }
                                });

                    }
                });
    }

    public void dialog2(){
        AlertDialog.Builder text = new AlertDialog.Builder(this);
        text.setTitle("New Password");
        text.setMessage("Enter Twice Please");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText newPass = new EditText(this);
        final EditText newPass2 = new EditText(this);
        newPass.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newPass.setSelection(newPass.getText().length());
        newPass2.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newPass2.setSelection(newPass2.getText().length());
        layout.addView(newPass);
        layout.addView(newPass2);
        text.setView(layout);



        text.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                final String contra1 = newPass.getText().toString();
                final String contra2 = newPass2.getText().toString();

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful() && contra1 != ""&& contra2 != ""&& contra1.equals(contra2)) {
                            ChangeInfoActivity.this.setPassword(contra1);
                            dialogInterface.dismiss();
                            updatePassword();
                            startFirstActivity();
                        }else {
                            dialog();
                            Toast.makeText(ChangeInfoActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        AlertDialog alertDialog = text.create();
        alertDialog.show();
    }
    public void startFirstActivity (){
        Intent intent = new Intent(ChangeInfoActivity.this,FirstActivity.class);
        startActivity(intent);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
