package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    Button logInButton;
    EditText Usua,Contra;
    String CheckUsua, CheckContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Usua = findViewById(R.id.Usua);
        Contra = findViewById(R.id.Contra);


        logInButton = findViewById(R.id.logButton   );
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckContra = String.valueOf(Contra.getText());
                CheckUsua = String.valueOf(Usua.getText());

                if (CheckUsua.equals("jorger@gmail.com") && CheckContra.equals("Pass123")) {
                    Intent intent = new Intent(LogInActivity.this, ActividadDiariaActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Usuario Inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
