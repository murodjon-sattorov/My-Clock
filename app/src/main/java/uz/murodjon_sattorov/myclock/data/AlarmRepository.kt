package uz.murodjon_sattorov.myclock.data

import androidx.lifecycle.LiveData
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/20/2021
 * @project My Clock
 */
class AlarmRepository(private val alarmDao: AlarmDao) {

    val readAllAlarm: LiveData<List<AlarmModel>> = alarmDao.readAllAlarm()
    val readAllClocks: LiveData<List<MyTimeZone>> = alarmDao.readAllClocks()

    suspend fun addNewAlarm(model: AlarmModel) {
        alarmDao.addNewAlarm(model)
    }

    suspend fun addNewClock(model: MyTimeZone) {
        alarmDao.addNewClock(model)
    }

    suspend fun updateAlarm(model: AlarmModel) {
        alarmDao.updateAlarm(model)
    }

    suspend fun deleteAlarm(model: AlarmModel) {
        alarmDao.deleteAlarm(model)
    }

    suspend fun deleteClock(model: MyTimeZone) {
        alarmDao.deleteClock(model)
    }

}