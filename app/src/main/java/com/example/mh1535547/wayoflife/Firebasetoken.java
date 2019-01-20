package com.example.mh1535547.wayoflife;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class Firebasetoken extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh(){
  String refreshedtoken=FirebaseInstanceId.getInstance().getToken();
        Log.d("Refreshed token:" , refreshedtoken);
   }
}
