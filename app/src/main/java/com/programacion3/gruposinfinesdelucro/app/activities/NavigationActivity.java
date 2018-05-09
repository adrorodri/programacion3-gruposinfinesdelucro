package com.programacion3.gruposinfinesdelucro.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.programacion3.gruposinfinesdelucro.app.R;
import com.programacion3.gruposinfinesdelucro.app.adapters.RoutinesAdapter;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView displayNameTextView, emailTextView;
    private ImageView profilePic;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        displayNameTextView = header.findViewById(R.id.displayNameTextView);
        emailTextView = header.findViewById(R.id.emailTextView);
        profilePic = header.findViewById(R.id.circle_profile);


        if (displayNameTextView == null) {
            Log.d("NavigationActivity", "Display name is null");
        }

        if (emailTextView == null) {
            Log.d("NavigationActivity", "Email is null");
        }

        View view = findViewById(R.id.nav_view);

        if (view == null) {
            Log.d("NavigationActivity", "Nav-view is null");
        }
        updateHeader();
        updatePicHeader();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_samu this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            dialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        final Intent intent;
        if (id == R.id.nav_perfil) {
            intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_actividad_diaria) {
            intent = new Intent(this, ActividadDiariaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rutinas) {
            intent = new Intent(this, RoutinesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_configuration) {
            intent = new Intent(NavigationActivity.this, ChangeInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_cerrar_sesión) {
            intent = new Intent(NavigationActivity.this, FirstActivity.class);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ALERTA!");
            builder.setMessage("Estas apunto de cerrar sesión ¿estas seguro?");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Toast.makeText(NavigationActivity.this, "Saliste exitosamente", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signOut();
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(NavigationActivity.this, "Cancelaste la acción", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

            positiveButton.setTextColor(Color.parseColor("#959595"));

            negativeButton.setTextColor(Color.parseColor("#959595"));


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateHeader() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        displayNameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
    }
    public void updatePicHeader(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Glide.with(NavigationActivity.this)
                .load(user.getPhotoUrl()).fitCenter().centerCrop().into(profilePic);
    }

    public void setToolbarTitle(String title){
        toolbar.setTitle(title);
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


        text.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String password = editPass.getText().toString();
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), password);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(NavigationActivity.this, ChangeInfoActivity.class);
                            intent.putExtra("key",password.toString());
                            startActivity(intent);
                        }else {
                            Toast.makeText(NavigationActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = text.create();
        alertDialog.show();
    }
}
