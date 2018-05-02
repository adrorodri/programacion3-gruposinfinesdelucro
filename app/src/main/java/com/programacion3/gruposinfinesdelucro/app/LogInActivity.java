package com.programacion3.gruposinfinesdelucro.app;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private Button logInButton;
    private EditText Usua, Contra;
    private TextView resetPasswordTextView, joinTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        Usua = findViewById(R.id.Usua);
        Contra = findViewById(R.id.Contra);
        resetPasswordTextView = findViewById(R.id.resetPasswordTextView);
        joinTextView = findViewById(R.id.joinTextView);

        Intent intent = getIntent();
        boolean signed_recently =  intent.getBooleanExtra("signed_recently", false);
        if(signed_recently){
            String email = intent.getStringExtra("user_email");
            Usua.setText(email);
        }

        logInButton = findViewById(R.id.logButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateForm()){
                    return;
                }

                signIn();
            }
        });

        resetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                builder.setMessage("¿Desea recibir un correro de restablecimiento de contraseña?")
                        .setTitle("Restablecimiento de contraseña")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetPassword();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        joinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateForm(){
        if(Usua.getText().toString().equals("")){
            Usua.requestFocus();
            Usua.setError("Campo oligatorio");
        }else if(Contra.getText().toString().equals("")){
            Contra.requestFocus();
            Contra.setError("Campo oligatorio");
        }else {
            return true;
        }
        return false;
    }

    private void signIn() {
        auth.signInWithEmailAndPassword(Usua.getText().toString(), Contra.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("LogInActivity", "Cuenta ingresó con éxito");
                            if(auth.getCurrentUser() == null){
                                return;
                            }

                            if(!auth.getCurrentUser().isEmailVerified()){
                                showDialog("Error de verificación", "El correo proporcionado no ha sido verificado");
                                auth.signOut();
                            }else{
                                startApp();
                            }
                        } else {
                            showDialog("Error", "No se pudo verificar el correo o contraseña");
                        }
                    }
                });

    }

    private void resetPassword(){
        auth = FirebaseAuth.getInstance();
        String email = Usua.getText().toString();
        if(TextUtils.isEmpty(email)){
            showDialog("Error", "No se pudo enviar el correo de restablecimiento" +
                    " de contraseña");
            return;
        }


        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            showDialog("Restablecer contraseña", "Correo de restablecimiento de contraseña" +
                                    "ha sido enviado con éxito");
                        }else{
                            showDialog("Error", "No se pudo enviar el correo de restablecimiento" +
                                    " de contraseña");
                        }
                    }
                });
    }

    private void startApp() {
        Intent intent = new Intent(this, ActividadDiariaActivity.class);
        startActivity(intent);
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("LogInActivity", "User agrees");
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
