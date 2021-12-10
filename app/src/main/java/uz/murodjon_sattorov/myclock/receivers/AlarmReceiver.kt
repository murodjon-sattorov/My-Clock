package uz.murodjon_sattorov.myclock.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.views.activities.MainActivity

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/21/2021
 * @project My Clock
 */
class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val i = Intent(context, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context!!, "alarm_channel")
            .setSmallIcon(R.drawable.ic_round_access_alarm_24)
            .setContentTitle("Alarm manager test")
            .setContentText("this is content text for test")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_countdown_timer_24, "Snooze", pendingIntent)

        builder.createHeadsUpContentView()

        val managerCompat: NotificationManagerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(0, builder.build())

    }
}