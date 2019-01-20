package com.example.mh1535547.wayoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);
    Splashscreen splash= new Splashscreen();
    splash.start();
    }
    private class Splashscreen extends Thread{
        @Override
        public void run() {
            try{
                sleep(3000);

            }
            catch(InterruptedException e){
                e.printStackTrace();

            }
            Intent intent = new Intent(splashscreen.this,Home_page.class);
            startActivity(intent);
            splashscreen.this.finish();
        }
    }

}
