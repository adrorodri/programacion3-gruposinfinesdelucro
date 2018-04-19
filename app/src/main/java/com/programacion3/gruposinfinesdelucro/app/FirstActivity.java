package com.programacion3.gruposinfinesdelucro.app;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button joinButton, logInButton;

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        joinButton = findViewById(R.id.joinButton);
        logInButton = findViewById(R.id.logInButton);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogInActviy();
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUpActivity();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Integer[] images = {R.drawable.slide, R.drawable.slide2};

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, images);

        viewPager.setAdapter(viewPagerAdapter);
    }

    public void startLogInActviy(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void startSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
