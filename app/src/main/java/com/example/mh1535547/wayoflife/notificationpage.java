package com.example.mh1535547.wayoflife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.widget.TextView;


public class notificationpage extends AppCompatActivity {
    private Toolbar toolbar;
 private DrawerLayout mdrawer;
 private ActionBarDrawerToggle toggle;
 TextView msg,titlemsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notificationpage);
        Toolbar toolbar = findViewById(R.id.tool);
titlemsg=findViewById(R.id.msg_title);
msg=findViewById(R.id.message);
LocalBroadcastManager.getInstance(this).registerReceiver(mHandler,new IntentFilter("com.example.mh1535547.wayoflife_FCM_MESSAGE"));
        mdrawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, mdrawer,toolbar,
                R.string.navigation_drawer_close,R.string.navigation_drawer_open );
        mdrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed() {
        if(mdrawer.isDrawerOpen(GravityCompat.START)){
            mdrawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
public BroadcastReceiver mHandler=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        titlemsg.setText(title);
        msg.setText(message);

    }
};

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandler);
    }
}
