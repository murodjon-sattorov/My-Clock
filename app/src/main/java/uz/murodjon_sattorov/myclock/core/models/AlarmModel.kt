package uz.murodjon_sattorov.myclock.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/20/2021
 * @project My Clock
 */
@Entity(tableName = "my_clock_table")
class AlarmModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var hour: Int,
    var minute: Int,
    var title: String,
    var vibrate: Boolean,
    var repeat: Boolean,
    var monday:Boolean,
    var tuesday:Boolean,
    var wednesday:Boolean,
    var thurday:Boolean,
    var friday:Boolean,
    var saturday:Boolean,
    var sunday:Boolean,
)