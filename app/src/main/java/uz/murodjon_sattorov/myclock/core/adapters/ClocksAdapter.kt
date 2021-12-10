package uz.murodjon_sattorov.myclock.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rm.rmswitch.RMSwitch
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.helpers.GetTimeZone
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import uz.murodjon_sattorov.myclock.databinding.ClockItemBinding
import uz.murodjon_sattorov.myclock.databinding.TimeZoneItemsBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/27/2021
 * @project My Clock
 */
class ClocksAdapter : RecyclerView.Adapter<ClocksAdapter.ViewHolder>() {

    private var data = ArrayList<MyTimeZone>()
    fun addData(d: List<MyTimeZone>) {
        data.clear()
        data.addAll(d)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ClockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(model: MyTimeZone) {
            binding.clockView.setTimeZone(model.zoneName)
            binding.cityName.text = model.title.subSequence(10, model.title.length)
            val tz: TimeZone = TimeZone.getTimeZone(model.zoneName)
            val calendar: Calendar = Calendar.getInstance(tz)
            binding.timeText.text = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.format(
                "%02d", calendar.get(Calendar.MINUTE))
            binding.dateDay.text = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)) + " " + calendar.get(Calendar.MONTH)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ClockItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.clock_item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}