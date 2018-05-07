package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.programacion3.gruposinfinesdelucro.app.R;

public class SignUpActivity extends AppCompatActivity {

    private Button ccButton;
    private RadioButton hombre, mujer;
    private CheckBox condiciones;
    private EditText nombre, correo, contra1, contra2;
    private String checkNombre, checkCorreo, checkContra1, checkContra2;
    private DatabaseReference database;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        ccButton = findViewById(R.id.crearCuenta);
        hombre = findViewById(R.id.bhombre);
        mujer = findViewById(R.id.bmujer);
        condiciones = findViewById(R.id.cond);

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contra1 = findViewById(R.id.pass);
        contra2 = findViewById(R.id.pass2);



        ccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateForm()) {
                    return;
                }
                createAccount();
            }
        });

    }

    private boolean validateForm(){
        checkNombre = String.valueOf(nombre.getText());
        checkCorreo = String.valueOf(correo.getText());
        checkContra1 = String.valueOf(contra1.getText());
        checkContra2 = String.valueOf(contra2.getText());

        if (TextUtils.isEmpty(checkNombre)) {
            nombre.requestFocus();
            nombre.setError("Campo obligatorio");

        } else if (TextUtils.isEmpty(checkCorreo)) {
            correo.requestFocus();
            correo.setError("Campo obligatorio");

        } else if (!checkContra1.equals(checkContra2) || TextUtils.isEmpty(checkContra1)) {
            contra1.requestFocus();
            contra2.requestFocus();
            contra1.setError("Las contraseñas no son iguales");
        } else if (!hombre.isChecked() && !mujer.isChecked()) {
            Toast.makeText(SignUpActivity.this, "Debes especificar un genero", Toast.LENGTH_SHORT).show();

        } else if (!condiciones.isChecked()) {
            Toast.makeText(SignUpActivity.this, "Debe aceptar las condiciones", Toast.LENGTH_SHORT).show();
        } else{
            return true;
        }
        return false;
    }

    private void sendEmailVerification() {
        final FirebaseUser user = auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Log.d("SignUpActivity", "Correo enviado con éxito");
                            showDialog("Verificación de correo", "Correo de verificación de cuenta fue enviado con éxito");
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage("Correo de verificación de cuenta fue enviado con éxito")
                                    .setTitle("Verificación de correo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            updateUserInfo();
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }else {
                            Log.d("SignUpActivity", "No se pudo enviar correo");
                            showDialog("Error", "No se pudo enviar el correo");
                        }
                    }
                });
    }

    private void createAccount() {
        auth.createUserWithEmailAndPassword(correo.getText().toString(), contra1.getText().toString())
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SignUpActivity", "Cuenta creada con éxito");
                            signIn();
                        } else {
                            // If sign in fails, display a message to the user.
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            showDialog("Error de verificación", "Failed Registration: "+e.getMessage());
                        }

                    }
                });
    }

    private void updateUserInfo(){
        FirebaseUser user = auth.getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nombre.getText().toString())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("SignUpActivity", "User profile updated.");
                            goToLoginActivity();
                        }else{
                            Log.d("SignUpActivity", "Couldnt update info");
                        }
                    }
                });
    }

    private void goToLoginActivity(){
        FirebaseUser user = auth.getCurrentUser();
        Intent intent = new Intent(this, LogInActivity.class);
        intent.putExtra("signed_recently", true);
        intent.putExtra("user_email", user.getEmail());
        auth.signOut();
        startActivity(intent);
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void signIn() {
        auth.signInWithEmailAndPassword(correo.getText().toString(), contra1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SignUpActivity", "Cuenta ingresó con éxito");
                            sendEmailVerification();
                        } else {
                            showDialog("Error", "No se puede acceder a la cuenta");
                        }
                    }
                });
    }
}

