package uz.murodjon_sattorov.myclock.core.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.receivers.CancelNotification
import uz.murodjon_sattorov.myclock.views.activities.MainActivity


/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 12/6/2021
 * @project My Clock
 */
class TimerNotification(private val context: Context) {

    fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelTimeRunning = NotificationChannel(
                CHANNEL_TIME_RUNNING,
                "Channel Time Running",
                NotificationManager.IMPORTANCE_LOW
            )
            channelTimeRunning.description = "This is Timer Running Channel"
            val channelTimeFinished = NotificationChannel(
                CHANNEL_TIME_FINISHED,
                "Channel Time Finished",
                NotificationManager.IMPORTANCE_HIGH
            )
            channelTimeFinished.description = "This is Timer Finished Channel"

            val manager =
                context.getSystemService(NotificationManager::class.java) as NotificationManager
            manager.createNotificationChannel(channelTimeRunning)
            manager.createNotificationChannel(channelTimeFinished)
        }
    }

    fun runNotification(time: String, notificationManager: NotificationManagerCompat) {
        val notification = NotificationCompat.Builder(context, CHANNEL_TIME_RUNNING)
            .setContentTitle(time)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_round_access_alarm_24)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        notificationManager.notify(1, notification)
    }

    fun finishNotification(notificationManager: NotificationManagerCompat) {
        val intent = Intent(context, CancelNotification::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(context, CHANNEL_TIME_FINISHED)
            .setContentTitle(TOTAL_TIME)
            .setSmallIcon(R.drawable.ic_round_access_alarm_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .addAction(R.drawable.ic_round_access_alarm_24, "OK", pendingIntent)
            .setContentText("Timer")
            .build()

        notificationManager.notify(1, notification)
    }

}