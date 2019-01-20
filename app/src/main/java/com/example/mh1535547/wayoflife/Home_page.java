package com.example.mh1535547.wayoflife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_page extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    Button adminlogin;
    Button userlogin;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_home_page);
    adminlogin=(Button)findViewById(R.id.adminlog);
    userlogin=(Button)findViewById(R.id.userlog);
firebaseAuth.getInstance();
adminlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i =new Intent(Home_page.this,Adminlogin.class);
       startActivity(i);
    }
});
userlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Home_page.this,loginpage.class);
        startActivity(intent);

    }
});


    }


}
