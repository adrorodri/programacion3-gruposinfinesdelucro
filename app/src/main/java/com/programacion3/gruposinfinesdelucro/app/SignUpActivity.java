package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button ccButton;
    RadioButton hombre, mujer;
    CheckBox condiciones;
    EditText nombre, correo, contra1, contra2;
    String checkNombre, checkCorreo, checkContra1, checkContra2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ccButton = findViewById(R.id.crearCuenta);
        hombre = (RadioButton) findViewById(R.id.bhombre);
        mujer = (RadioButton) findViewById(R.id.bmujer);
        condiciones = (CheckBox) findViewById(R.id.cond);
        ccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = (EditText) findViewById(R.id.nombre);
                correo = (EditText) findViewById(R.id.correo);
                contra1 = (EditText) findViewById(R.id.pass);
                contra2 = (EditText) findViewById(R.id.pass2);


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
                } else {

                    Intent intent = new Intent(SignUpActivity.this, SignUpSuccess.class);
                    startActivity(intent);
                }
            }
        });

    }

}

