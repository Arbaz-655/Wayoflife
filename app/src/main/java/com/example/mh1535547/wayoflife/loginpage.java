package com.example.mh1535547.wayoflife;

import android.app.Notification;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.ProgressDialog;
import android.widget.Toast;

public class loginpage extends AppCompatActivity {

    Button login;
    EditText email;
    private FirebaseAuth firebaseAuth;
    EditText password;
    TextView forgotten;
    TextView nuser;


    private ProgressDialog dialog;
    String user_email;
    DatabaseReference databaseReference;
    String em;

    String pass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loginpage);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.emailtext);
        password = (EditText) findViewById(R.id.passwordtext);
        login = (Button) findViewById(R.id.logintohome);
        forgotten = (TextView) findViewById(R.id.forget);
        nuser=(TextView) findViewById(R.id.newuser);
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                em = email.getText().toString().trim();
                databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        savedatatofirebase savedata = dataSnapshot.getValue(savedatatofirebase.class);
                        email.setText(savedata.email_id);
                        if (em.equals(email)) {
                            Toast.makeText(loginpage.this, "Welcome", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(loginpage.this, "sorry", Toast.LENGTH_LONG).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(loginpage.this, "couldnt", Toast.LENGTH_LONG).show();

                    }
                });
            }
            });


*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String em=email.getText().toString().trim();
                final String pswd=password.getText().toString().trim();

                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("email_id");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot ) {
                        String useremail=dataSnapshot.getValue(String.class);
                        if(em.equals(useremail)){

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("password");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String pass=dataSnapshot.getValue(String.class);
                                        if(pswd.equals(pass)){

                                            firebaseAuth.createUserWithEmailAndPassword(em,pswd);
                                            Toast.makeText(loginpage.this, "welcome", Toast.LENGTH_SHORT).show();


                                            Intent intent =new Intent(loginpage.this,notificationpage.class);
                                            startActivity(intent);


                                }else{
                                        password.setError("Password is not correct");
                                        forgotten.requestFocus();
                                        }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                       }
                       else{
                           email.setError("This email is not registered");
                           email.requestFocus();
                       }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }

        });

nuser.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(loginpage.this,signuppage.class);
        startActivity(intent);
    }
});
        //forgetten password textview

        forgotten.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(loginpage.this, forgetpassword.class);
                startActivity(intent);

            }


        });//forgotten


    }

    }

