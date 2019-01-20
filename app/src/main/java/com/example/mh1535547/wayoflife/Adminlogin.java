package com.example.mh1535547.wayoflife;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
public class Adminlogin extends AppCompatActivity {
    EditText usernm,pass;
    Button login;
    String usnm,pswd;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_adminlogin);
        firebaseAuth = FirebaseAuth.getInstance();
        usernm=(EditText) findViewById(R.id.usernameedit);
        pass=(EditText) findViewById(R.id.passwordedit);
        login=(Button) findViewById(R.id.loginbutton);

        //click listener on login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 usnm=usernm.getText().toString().trim();
                 pswd=pass.getText().toString().trim();
                if(usnm.equals("")){
                    Toast.makeText(Adminlogin.this,"Please enter your email id",Toast.LENGTH_LONG).show();
                    usernm.requestFocus();
                }
                else{
                    databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child("Email_id");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                        public void onDataChange(DataSnapshot dataSnapshot ) {
                            String adminemail=dataSnapshot.getValue(String.class);
                            if(usnm.equals(adminemail)){
                                if(pswd.equals("")){
                                    Toast.makeText(Adminlogin.this,"Please enter your password",Toast.LENGTH_LONG).show();
                                    pass.requestFocus();
                                }
                                else{
                                    databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child("password");
                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String adminpassword=dataSnapshot.getValue(String.class);
                                            if(pswd.equals(adminpassword)){
                                                Toast.makeText(Adminlogin.this,"Refirecting...",Toast.LENGTH_LONG).show();


                                            }
                                            else{
                                                Toast.makeText(Adminlogin.this,"please enter correct password",Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                            else{
                                Toast.makeText(Adminlogin.this,"Please enter correct Email_id",Toast.LENGTH_LONG).show();
                            }
                            }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                        });

                }




            }
        });

    }
}
