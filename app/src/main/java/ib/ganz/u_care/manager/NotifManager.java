package ib.ganz.u_care.manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.core.app.NotificationCompat;

import ib.ganz.u_care.R;

public class NotifManager {
    public static void createNotification(Context c, int id, PendingIntent pi, String judul, String isi)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationManager man = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = "default_channel_id";
            String channelDescription = "Default Channel";

            NotificationChannel notificationChannel = man.getNotificationChannel(channelId);
            if (notificationChannel == null)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelId, channelDescription, importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                man.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(c, channelId);
            builder.setContentTitle(judul)  // required
                    .setSmallIcon(R.drawable.logo) // required
                    .setLargeIcon(BitmapFactory.decodeResource(c.getResources(), R.drawable.logo))
                    .setContentText(isi)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .setTicker(judul);

            man.notify(id, builder.build());

            playBeep(c);
        }
        else
        {
            Notification n = new Notification.Builder(c)
                    .setTicker(judul)
                    .setContentTitle(judul)
                    .setContentText(isi)
                    .setSmallIcon(R.drawable.logo)
                    .setLargeIcon(BitmapFactory.decodeResource(c.getResources(), R.drawable.logo))
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .build();

            NotificationManager man = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
            if (man != null)
            {
                man.notify(id, n);
            }

            playBeep(c);
        }
    }

    private static void playBeep(Context c)
    {
        try
        {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(c, notification);
            r.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static float getImageFactor(Resources r)
    {
        DisplayMetrics metrics = r.getDisplayMetrics();
        return metrics.density / 4f;
    }
}
