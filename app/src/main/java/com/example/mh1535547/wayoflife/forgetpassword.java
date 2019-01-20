package com.example.mh1535547.wayoflife;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;

import android.Manifest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

   EditText email;
   Button  submit;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgetpassword);
       email=(EditText) findViewById(R.id.Emailidedit);
       submit=(Button) findViewById(R.id.submitbutton);
       firebaseAuth=FirebaseAuth.getInstance();
       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String user_email=email.getText().toString().trim();
               if(user_email.isEmpty()){
                   Toast.makeText(forgetpassword.this, "Please enter   email-id", Toast.LENGTH_SHORT).show();
               }//ifuseremail

               else{
                   firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(forgetpassword.this,"mail sent",Toast.LENGTH_LONG).show();
                               Intent intent =new Intent(forgetpassword.this,loginpage.class);
                               startActivity(intent);
                           }//is successful
                       else{
                               Toast.makeText(forgetpassword.this,"mail not sent",Toast.LENGTH_LONG).show();
                           }//else
                       }

                   });//oncomplete listener
               }
           }
       });



    }




    }


