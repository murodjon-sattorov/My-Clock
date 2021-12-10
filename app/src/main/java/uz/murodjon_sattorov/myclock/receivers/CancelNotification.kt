package uz.murodjon_sattorov.myclock.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 12/6/2021
 * @project My Clock
 */
class CancelNotification: BroadcastReceiver() {
    private var notificationManager: NotificationManagerCompat? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager!!.cancel(1)
    }
}