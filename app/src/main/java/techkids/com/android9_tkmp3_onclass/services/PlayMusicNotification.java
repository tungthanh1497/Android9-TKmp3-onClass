package techkids.com.android9_tkmp3_onclass.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import techkids.com.android9_tkmp3_onclass.MainActivity;
import techkids.com.android9_tkmp3_onclass.R;
import techkids.com.android9_tkmp3_onclass.databases.TopSongModel;
import techkids.com.android9_tkmp3_onclass.managers.MusicManager;

/**
 * Created by tungthanh.1497 on 07/29/2017.
 */

public class PlayMusicNotification extends BroadcastReceiver {
    public static NotificationCompat.Builder builder;
    public static RemoteViews remoteViews;
    public static NotificationManager notificationManager;

    public static void setupNotification(Context context, TopSongModel topSongModel) {
        //remote view
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_name_song, topSongModel.getName());
        remoteViews.setTextViewText(R.id.tv_artist_song, topSongModel.getAuthor());

        //pending
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent switchIntent = new Intent("com.example.app.ACTION_PLAY");
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 100, switchIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_button, pendingSwitchIntent);


        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.mp3_icon_white)
                .setContent(remoteViews)
                .setOngoing(true)
                .setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public static void updateNotification(boolean isPlaying) {
        if (isPlaying) {
            remoteViews.setImageViewResource(R.id.iv_play_button, R.drawable.ic_pause_black_24dp);
        } else {
            remoteViews.setImageViewResource(R.id.iv_play_button, R.drawable.ic_play_arrow_black_24dp);
        }
        notificationManager.notify(0, builder.build());
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equalsIgnoreCase("com.example.app.ACTION_PLAY")) {
            // do your stuff to play action;
            Toast.makeText(context, "Jump in", Toast.LENGTH_SHORT).show();
            MusicManager.playPauseMusic();
            updateNotification(MusicManager.mediaPlayer.isPlaying());
            Toast.makeText(context, "Jump out", Toast.LENGTH_SHORT).show();
        }
    }
}
