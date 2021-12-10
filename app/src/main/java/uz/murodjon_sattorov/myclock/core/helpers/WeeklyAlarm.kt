package uz.murodjon_sattorov.myclock.core.helpers

import ca.antonious.materialdaypicker.MaterialDayPicker
import java.util.*

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/24/2021
 * @project My Clock
 */
object WeeklyAlarm {

    var monday = false;
    var tuesday = false;
    var wednesday = false;
    var thursday = false;
    var friday = false;
    var saturday = false;
    var sunday = false

    fun setSelectedDays(days: MaterialDayPicker) {
        days.setDayPressedListener { weekday, isSelected ->
            when (weekday.name) {
                "MONDAY" -> monday = isSelected
                "TUESDAY" -> tuesday = isSelected
                "WEDNESDAY" -> wednesday = isSelected
                "THURSDAY" -> thursday = isSelected
                "FRIDAY" -> friday = isSelected
                "SATURDAY" -> saturday = isSelected
                "SUNDAY" -> sunday = isSelected
            }
        }
    }

}