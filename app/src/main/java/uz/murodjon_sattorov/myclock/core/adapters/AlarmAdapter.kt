package uz.murodjon_sattorov.myclock.core.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.rm.rmswitch.RMSwitch
import com.zerobranch.layout.SwipeLayout
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.databinding.AlarmItemBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/22/2021
 * @project My Clock
 */
class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    private var data = ArrayList<AlarmModel>()
    var setOnClickListener: SetOnClickListener? = null
    fun addData(d: List<AlarmModel>) {
        data.clear()
        data.addAll(d)
        notifyDataSetChanged()
    }

    fun setOnClick(setOnClickListener: SetOnClickListener) {
        this.setOnClickListener = setOnClickListener
    }

    inner class ViewHolder(var binding: AlarmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(model: AlarmModel) {
            binding.loadTimeView.text =
                String.format("%02d", model.hour) + ":" + String.format("%02d", model.minute)
            binding.labelView.text = model.title
            binding.repeatAlarm.isChecked = model.repeat

            binding.alarmItem.setOnClickListener {
                setOnClickListener!!.onUpdateClick(model)
            }
            binding.repeatAlarm.setOnClickListener {
                setOnClickListener!!.onRepeatClick(model, it as RMSwitch)
            }

            binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
                override fun onOpen(direction: Int, isContinuous: Boolean) {
                    setOnClickListener!!.onDeleteSwipe(model)
                    Toast.makeText(
                        binding.swipeLayout.context,
                        "Deleted item" + model.id,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onClose() {

                }

            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AlarmItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
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
        fun onUpdateClick(model: AlarmModel)

        fun onRepeatClick(model: AlarmModel, view: RMSwitch)

        fun onDeleteSwipe(model: AlarmModel)
    }

}