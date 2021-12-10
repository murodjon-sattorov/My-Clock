package uz.murodjon_sattorov.myclock.views.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.rm.rmswitch.RMSwitch
import uz.murodjon_sattorov.myclock.core.helpers.SharedPref
import uz.murodjon_sattorov.myclock.core.helpers.WeeklyAlarm
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.databinding.AddNewAlarmDialogBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/25/2021
 * @project My Clock
 */
class AlarmUpdateDialog(context: Context, model: AlarmModel) : AlertDialog(context) {

    private var dialogBinding: AddNewAlarmDialogBinding =
        AddNewAlarmDialogBinding.inflate(LayoutInflater.from(context))

    private var getCheckVibrateAndLabel: GetCheckVibrateAndLabel? = null
    fun setOnCheckVibrateAndLabel(getCheckVibrateAndLabel: GetCheckVibrateAndLabel) {
        this.getCheckVibrateAndLabel = getCheckVibrateAndLabel
    }

    init {
        dialogBinding.alarmTimePickerHour.value = model.hour
        dialogBinding.alarmTimePickerMinute.value = model.minute
        dialogBinding.isVibrate.isChecked = model.vibrate
        dialogBinding.inputLabel.setText(model.title)

        val list = ArrayList<MaterialDayPicker.Weekday>()

        var monday: MaterialDayPicker.Weekday? = null
        var tuesday: MaterialDayPicker.Weekday? = null
        var wednesday: MaterialDayPicker.Weekday? = null
        var thursday: MaterialDayPicker.Weekday? = null
        var friday: MaterialDayPicker.Weekday? = null
        var saturday: MaterialDayPicker.Weekday? = null
        var sunday: MaterialDayPicker.Weekday? = null

        if (model.monday) {
            monday = MaterialDayPicker.Weekday.MONDAY
            list.add(monday)
        }
        if (model.tuesday) {
            tuesday = MaterialDayPicker.Weekday.TUESDAY
            list.add(tuesday)
        }
        if (model.wednesday) {
            wednesday = MaterialDayPicker.Weekday.WEDNESDAY
            list.add(wednesday)
        }
        if (model.thurday) {
            thursday = MaterialDayPicker.Weekday.WEDNESDAY
            list.add(thursday)
        }
        if (model.friday) {
            friday = MaterialDayPicker.Weekday.FRIDAY
            list.add(friday)
        }
        if (model.saturday) {
            saturday = MaterialDayPicker.Weekday.SATURDAY
            list.add(saturday)
        }
        if (model.sunday) {
            sunday = MaterialDayPicker.Weekday.SUNDAY
            list.add(sunday)
        }
        dialogBinding.dayPicker.setSelectedDays(list)

        WeeklyAlarm.setSelectedDays(dialogBinding.dayPicker)

        dialogBinding.okText.setOnClickListener {
            getCheckVibrateAndLabel!!.getVibrateAndLabel(
                model.id,
                String.format("%02d", dialogBinding.alarmTimePickerHour.value),
                String.format("%02d", dialogBinding.alarmTimePickerMinute.value),
                dialogBinding.isVibrate.isChecked,
                true,
                dialogBinding.inputLabel.text.toString(),
                WeeklyAlarm.monday,
                WeeklyAlarm.tuesday,
                WeeklyAlarm.wednesday,
                WeeklyAlarm.thursday,
                WeeklyAlarm.friday,
                WeeklyAlarm.saturday,
                WeeklyAlarm.sunday
            )
            dismiss()
            cancel()
        }
        dialogBinding.cancelText.setOnClickListener {
            dismiss()
            cancel()
        }

        setView(dialogBinding.root)
    }

}