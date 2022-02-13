package com.e_wayalerts.FireBaseMessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class CustomFireBaseMessasing extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static final String MyNoti="CHANNEL_ID";
    public int count=0;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        try {
            if(remoteMessage !=null && remoteMessage.getNotification().getBody()!=null)
            {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
                Log.d(TAG, "Message Notification Title  : " + remoteMessage.getNotification().getTitle());

                getNotifaction(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

                Log.e("remote_msg==",remoteMessage.getNotification().getBody());
            }


            if(remoteMessage != null && remoteMessage.getData().size()>0)
            {

                count=count+1;
                Log.e("remote_msg_size==",remoteMessage.getData().toString());
                createNotificationChannel();
                getNotifaction(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("FCM_Error_Msg",e.getMessage());
        }

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);


        //Utility.setSharedPreference(mContext,
             //   Constant.FCmtoken,s);
        Log.e("onNewToken",s);

    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(MyNoti, name, importance);
            channel.setDescription(description);
            channel.canShowBadge();
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }


    private void getNotifaction(String title, String body) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, MyNoti)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body).setAutoCancel(true).setContentIntent(pendingIntent);;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(MyNoti, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());

    }

}
