package com.example.mh1535547.wayoflife;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.regex.Pattern;

public class signuppage extends AppCompatActivity {
 Button signup;
 EditText pswd;
 EditText cpswd;
 EditText emailid;
 EditText mobile;
 EditText fname;
 EditText lname;
 Spinner spinnercity,spinnerstate;

 FirebaseAuth firebaseAuth;
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signuppage);

        signup=(Button) findViewById(R.id.signupbutton) ;
        pswd=(EditText)findViewById(R.id.passwordedit) ;
        cpswd=(EditText)findViewById(R.id.confirmpasswordedit) ;
        emailid=(EditText)findViewById(R.id.emailidedit) ;
        mobile=(EditText)findViewById(R.id.mobilenoedit) ;
        fname=(EditText) findViewById(R.id.firstnameedit);
        lname=(EditText) findViewById(R.id.lastnameedit);
        spinnercity=(Spinner) findViewById(R.id.spinner_city);
        spinnerstate = (Spinner) findViewById(R.id.spinner_state);
        spinnercity.setEnabled(false);


       spinnerstate.setOnItemSelectedListener(new OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               spinnercity.setEnabled(true);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
               String val=parent.getItemAtPosition(0).toString();
               if(val.equals(""))
                spinnercity.setEnabled(false);
           }
       });
        //click listener on signup button
      signup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              boolean res=validateform();
              if(res==true){
                  adddata();

              }

          }
      });




    }
 private boolean validatepassword() {
     boolean temp = true;
     String pass = pswd.getText().toString();
 int minlength=8;

     String cpass = cpswd.getText().toString();

     if (!pass.equals(cpass)) {
         cpswd.setError("Password not matching");
         temp = false;
     } else if (pass.equals("")) {
         pswd.setError("Please enter password");
         temp = false;

     } else if (pass.length()<minlength){
         pswd.setError("minimum password length is 8");
         temp=false;
     }
     return temp;
     }
     private boolean validatefname(){
       String fn=fname.getText().toString();

        boolean tempfname=true;
        if(fn.matches("")){
            fname.setError("Enter your good name!");
            tempfname=false;
        }
        else if(fn.length()>20){
            fname.setError("Enter only your first name");
            tempfname=false;
        }
        return tempfname;
     }
     private boolean validatelname(){
         String ln=lname.getText().toString();
         boolean templname=true;
         if(ln.matches("")){
             lname.setError("Enter your last name");
             templname=false;
         }

         else if(ln.length()>20){
             lname.setError("Enter only your last name");
             templname=false;
         }
         return templname;
    }
     private boolean validateemail(){
            boolean temp1=true;
         String  em=emailid.getText().toString();
         if(!EMAIL_ADDRESS_PATTERN.matcher(em).matches()){
           emailid.setError("Enter valid email id");
             temp1=false;
         }
         else if ( em.equals("")){
             emailid.setError("Enter email id");
             temp1=false;
         }
         return temp1;
    }
    private boolean validatemobile(){
        String  mob=mobile.getText().toString();
        boolean temp2=true;
        String MobilePattern = "[0-9]{10}";
        if(mob.matches(MobilePattern)){
            temp2=true;
        }
        else if (!mob.matches(MobilePattern)){
            temp2=false;
        }
        return temp2;
    }
    private boolean validateform(){
        boolean vp=validatepassword();
        boolean ve=validateemail();
        boolean vm=validatemobile();
        boolean vfn=validatefname();
        boolean vln=validatelname();
        boolean tempform=true;
        if(ve==true && vp==true && vm==true && vfn==true && vln==true){
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
           tempform=true;
        }
        else{
            Toast.makeText(getApplicationContext(), "Sorry!!", Toast.LENGTH_LONG).show();
            tempform=false;
        }


        return tempform;
    }
    private void adddata(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users");
        spinnercity=(Spinner) findViewById(R.id.spinner_city);
        spinnerstate = (Spinner) findViewById(R.id.spinner_state);
        String em=emailid.getText().toString().trim();
        String mob=mobile.getText().toString().trim();
        String fn=fname.getText().toString().trim();
        String ln=lname.getText().toString().trim();
        String pass=pswd.getText().toString().trim();
        String city=String.valueOf(spinnercity.getSelectedItem());
        String state=String.valueOf(spinnerstate.getSelectedItem());
        savedatatofirebase savedata=new savedatatofirebase(fn,ln,pass,em,city,state,mob);

            databaseReference.push().setValue(savedata);

    }
}