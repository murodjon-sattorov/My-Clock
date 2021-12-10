package uz.murodjon_sattorov.myclock.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.models.StopwatchModel
import uz.murodjon_sattorov.myclock.databinding.StopwatchListBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 12/4/2021
 * @project My Clock
 */
class StopwatchAdapter : RecyclerView.Adapter<StopwatchAdapter.ViewModel>() {

    private var data = ArrayList<StopwatchModel>()
    fun addData(d: List<StopwatchModel>) {
        data.clear()
        data.addAll(d)
        notifyDataSetChanged()
    }

    inner class ViewModel(var binding: StopwatchListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: StopwatchModel) {
            binding.listId.text = model.id.toString()
            binding.timeText.text = model.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        return ViewModel(
            StopwatchListBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.stopwatch_list, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun clear(){
        data.clear()
    }
}