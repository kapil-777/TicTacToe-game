package com.dev3.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //handler class for executing the thread by using postDelay() method :
        //by using Runnable class  object and
        // abstract method run() method : inside this method we put the code that need to be execute after some delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(Splash.this,MidActivity.class);
                startActivity(home);
                finish();
            }
        },700);
    }
}