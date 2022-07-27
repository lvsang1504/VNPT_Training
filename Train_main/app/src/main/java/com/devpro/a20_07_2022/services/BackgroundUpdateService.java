//package com.devpro.a20_07_2022.services;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Handler;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.core.app.NotificationCompat;
//import androidx.core.content.ContextCompat;
//
//import com.devpro.a20_07_2022.R;
//import com.devpro.a20_07_2022.activities.MainActivity;
//
//import java.util.concurrent.TimeUnit;
//
//
//public class BackgroundUpdateService extends Service {
//
//    /**
//     * Author：Hardik Talaviya
//     * Date：  2019.08.3 2:30 PM
//     * Describe:
//     */
//
//    private static final String TAG = "BackgroundLocation";
//    private Context context;
//    private boolean stopService = false;
//    private Handler handler;
//    private Runnable runnable;
//    private NotificationCompat.Builder builder = null;
//    private NotificationManager notificationManager;
//
//    @Override
//    public void onCreate() {
//        Log.e(TAG, "Background Service onCreate :: ");
//        super.onCreate();
//        context = this;
//
//        handler = new Handler();
//        runnable = new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    //Add Here your code if you want start in background
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    handler.postDelayed(this, TimeUnit.SECONDS.toMillis(2));
//                }
//            }
//        };
//        if (!stopService) {
//            handler.postDelayed(runnable, TimeUnit.SECONDS.toMillis(2));
//        }
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onTaskRemoved(Intent rootIntent) {
//        super.onTaskRemoved(rootIntent);
//        Log.e(TAG, "onTaskRemoved :: ");
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.e(TAG, "onStartCommand :: ");
//        StartForeground();
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.e(TAG, "BackgroundService onDestroy :: ");
//        stopService = true;
//        if (handler != null) {
//            handler.removeCallbacks(runnable);
//        }
//    }
//
//    /*-------- For notification ----------*/
//    private void StartForeground() {
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        String CHANNEL_ID = "channel_location";
//        String CHANNEL_NAME = "channel_location";
//
//        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//            notificationManager.createNotificationChannel(channel);
//            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//            builder.setColorized(false);
//            builder.setChannelId(CHANNEL_ID);
//            builder.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
//            builder.setBadgeIconType(NotificationCompat.BADGE_ICON_NONE);
//        } else {
//            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
//        }
//        builder.setOnlyAlertOnce(true);
//        builder.setContentTitle(context.getResources().getString(R.string.app_name));
//        builder.setContentText("Your Text");
//        Uri notificationSound = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(notificationSound);
//        builder.setAutoCancel(true);
//        builder.setSmallIcon(R.drawable.ic_round_notifications_24);
//        builder.setContentIntent(pendingIntent);
//        startForeground(101, builder.build());
//    }
//}
