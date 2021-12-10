package uz.murodjon_sattorov.myclock.data

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/20/2021
 * @project My Clock
 */
@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewAlarm(model: AlarmModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewClock(model: MyTimeZone)

    @Update
    suspend fun updateAlarm(model: AlarmModel)

    @Delete
    suspend fun deleteAlarm(model: AlarmModel)

    @Delete
    suspend fun deleteClock(model: MyTimeZone)

    @Query("SELECT * FROM my_clock_table ORDER BY id ASC")
    fun readAllAlarm(): LiveData<List<AlarmModel>>

    @Query("SELECT * FROM clocks_table ORDER BY id ASC")
    fun readAllClocks(): LiveData<List<MyTimeZone>>

}