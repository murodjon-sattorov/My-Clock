package uz.murodjon_sattorov.myclock.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/26/2021
 * @project My Clock
 */
@Entity(tableName = "clocks_table")
data class MyTimeZone(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var zoneName: String
)