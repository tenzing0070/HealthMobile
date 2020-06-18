package com.dawa.boardcast;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dawa.createchannel.CreateChannel;
import com.dawa.mobilehealth.R;



public class BroadCastReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadCastReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;
        notificationManagerCompat= NotificationManagerCompat.from(context);
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                    false
            );
            if(noConnectivity){
                Toast.makeText(context,"Disconnected", Toast.LENGTH_SHORT).show();
                DisplayNotification();
            }
            else{
                Toast.makeText(context,"Connected", Toast.LENGTH_SHORT).show();
                DisplayNotification2();
            }
        }

    }

    private void DisplayNotification() {
        Notification notification=new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.checked)
                .setContentTitle("mHealth")
                .setContentText("No internet connection, please connect")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(1,notification);
    }

    private void DisplayNotification2() {
        Notification notification=new NotificationCompat.Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.checked)
                .setContentTitle("Mobile Health")
                .setContentText("You have been connected to a network")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(2,notification);
    }

}