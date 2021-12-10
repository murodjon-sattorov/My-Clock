package uz.murodjon_sattorov.myclock.views.dialogs

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import ca.antonious.materialdaypicker.MaterialDayPicker
import com.shawnlin.numberpicker.NumberPicker
import uz.murodjon_sattorov.myclock.core.helpers.SharedPref
import uz.murodjon_sattorov.myclock.core.helpers.WeeklyAlarm
import uz.murodjon_sattorov.myclock.databinding.AddNewAlarmDialogBinding
import java.util.*

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/22/2021
 * @project My Clock
 */
class AlarmSetDialog(context: Context) : AlertDialog(context) {

    private var dialogBinding: AddNewAlarmDialogBinding =
        AddNewAlarmDialogBinding.inflate(LayoutInflater.from(context))

    private var getCheckVibrateAndLabel: GetCheckVibrateAndLabel? = null
    fun setOnCheckVibrateAndLabel(getCheckVibrateAndLabel: GetCheckVibrateAndLabel) {
        this.getCheckVibrateAndLabel = getCheckVibrateAndLabel
    }

    init {
        dialogBinding.alarmTimePickerHour.value = Calendar.getInstance().time.hours
        dialogBinding.alarmTimePickerMinute.value = Calendar.getInstance().time.minutes
        WeeklyAlarm.setSelectedDays(dialogBinding.dayPicker)

        dialogBinding.okText.setOnClickListener {
            getCheckVibrateAndLabel!!.getVibrateAndLabel(
                0,
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