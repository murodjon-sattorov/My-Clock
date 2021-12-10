package uz.murodjon_sattorov.myclock.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.murodjon_sattorov.myclock.data.AlarmDatabase
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import uz.murodjon_sattorov.myclock.data.AlarmRepository

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/22/2021
 * @project My Clock
 */
class AlarmViewModel(application: Application) : AndroidViewModel(application) {

    val readAllAlarm: LiveData<List<AlarmModel>>
    val readAllClocks: LiveData<List<MyTimeZone>>
    private val repository: AlarmRepository

    init {
        val alarmDao = AlarmDatabase.getInstance(application).alarmDao()
        repository = AlarmRepository(alarmDao)
        readAllAlarm = repository.readAllAlarm
        readAllClocks = repository.readAllClocks
    }

    fun addAlarm(model: AlarmModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewAlarm(model)
        }
    }

    fun addClock(model: MyTimeZone) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewClock(model)
        }
    }

    fun updateAlarm(model: AlarmModel) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.updateAlarm(model)
        }
    }

    fun deleteAlarm(model: AlarmModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAlarm(model)
        }
    }

    fun deleteClock(model: MyTimeZone) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClock(model)
        }
    }

}