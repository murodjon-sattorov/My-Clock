package uz.murodjon_sattorov.myclock.core.helpers

import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import java.util.*

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/27/2021
 * @project My Clock
 */
object GetTimeZone {

    val scope = MainScope()
    var job: Job? = null

    fun startUpdates(model: MyTimeZone, view: TextView) {
        stopUpdates()
        job = scope.launch {
            while (true) {
                val tz: TimeZone = TimeZone.getTimeZone(model.zoneName)
                val calendar: Calendar = Calendar.getInstance(tz)
                val time =
                    String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.format(
                        "%02d",
                        calendar.get(Calendar.MINUTE)
                    ) + ":" + String.format("%02d", calendar.get(Calendar.SECOND))
                view.text = time
                delay(1000)
            }
        }
    }

    fun setAllClock(model: MyTimeZone, view: TextView) {
        val tz: TimeZone = TimeZone.getTimeZone(model.zoneName)
        val calendar: Calendar = Calendar.getInstance(tz)
        val time =
            String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                    String.format("%02d", calendar.get(Calendar.MINUTE))
        view.text = time
    }

    fun stopUpdates() {
        job?.cancel()
        job = null
    }

}