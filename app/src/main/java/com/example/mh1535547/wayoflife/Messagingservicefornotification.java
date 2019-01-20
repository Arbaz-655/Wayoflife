package com.example.mh1535547.wayoflife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Messagingservicefornotification extends FirebaseMessagingService {
    private static  final String Reg_token="REG_TOKEN";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size()>0){
            String title=remoteMessage.getData().get("title");
            String message=remoteMessage.getData().get("message");
            Intent intent=new Intent("com.example.mh1535547.wayoflife_FCM_MESSAGE");
            intent.putExtra("title",title);
            intent.putExtra("message",message);
            LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(intent);

        }
    }

    @Override
    public void onNewToken(String s) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(Messagingservicefornotification.this,"Error",Toast.LENGTH_LONG).show();
                }
                else{
                String token = task.getResult().getToken();
                    Log.d("Token",token);
                    Toast.makeText(Messagingservicefornotification.this,"token sent",Toast.LENGTH_LONG).show();
            }
            }
        });
    }
}
