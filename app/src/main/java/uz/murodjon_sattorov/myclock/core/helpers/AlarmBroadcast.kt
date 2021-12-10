package uz.murodjon_sattorov.myclock.core.helpers

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import uz.murodjon_sattorov.myclock.receivers.AlarmReceiver
import java.util.*

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/23/2021
 * @project My Clock
 */
class AlarmBroadcast {

    private var calendar: Calendar? = null
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    fun setAlarm(context: Context, hour: Int, minutes: Int, requestCode: Int) {
        Log.d("SSS", "setAlarm1: ")
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)

        calendar = Calendar.getInstance()
        calendar!!.set(Calendar.HOUR_OF_DAY, hour)
        calendar!!.set(Calendar.MINUTE, minutes)
        calendar!!.set(Calendar.SECOND, 0)
        calendar!!.set(Calendar.MILLISECOND, 0)

        Log.d("SSS", "setAlarm2: ")

        alarmManager!!.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar!!.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

//        alarmManager!!.setExact(AlarmManager.RTC_WAKEUP, calendar!!.timeInMillis, pendingIntent)

        Toast.makeText(context, "Alarm set successfully", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context, requestCode: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)

        if (alarmManager == null) {
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }

        alarmManager?.cancel(pendingIntent)
        Toast.makeText(context, "Alarm canceled", Toast.LENGTH_SHORT).show()
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Alarm"
            val description = "Alarm description"

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("alarm_channel", name, importance)
            channel.description = description

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }


}