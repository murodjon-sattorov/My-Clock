package uz.murodjon_sattorov.myclock.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rm.rmswitch.RMSwitch
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.helpers.GetTimeZone
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import uz.murodjon_sattorov.myclock.databinding.TimeZoneItemsBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/27/2021
 * @project My Clock
 */
class TimeZoneAdapter : RecyclerView.Adapter<TimeZoneAdapter.ViewHolder>() {

    private var data = ArrayList<MyTimeZone>()
    var setOnClickListener: SetOnClickListener? = null
    fun addData(d: List<MyTimeZone>) {
        data.clear()
        data.addAll(d)
        notifyDataSetChanged()
    }

    fun setOnClick(setOnClickListener: SetOnClickListener) {
        this.setOnClickListener = setOnClickListener
    }

    inner class ViewHolder(var binding: TimeZoneItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: MyTimeZone) {
            binding.timeZoneCity.text = model.title
            GetTimeZone.setAllClock(model, binding.liveTime)
            binding.clickItem.setOnClickListener {
                setOnClickListener!!.onClick(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TimeZoneItemsBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.time_zone_items, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface SetOnClickListener {
        fun onClick(model: MyTimeZone)
    }

}