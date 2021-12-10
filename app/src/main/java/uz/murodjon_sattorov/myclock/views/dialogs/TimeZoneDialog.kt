package uz.murodjon_sattorov.myclock.views.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import uz.murodjon_sattorov.myclock.core.adapters.TimeZoneAdapter
import uz.murodjon_sattorov.myclock.core.helpers.getAllTimeZones
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import uz.murodjon_sattorov.myclock.databinding.TimeZoneDialogBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/27/2021
 * @project My Clock
 */
class TimeZoneDialog(context: Context) : AlertDialog(context) {

    private var dialogBinding: TimeZoneDialogBinding =
        TimeZoneDialogBinding.inflate(LayoutInflater.from(context))

    private var adapter = TimeZoneAdapter()
    private var data = ArrayList<MyTimeZone>()

    private var setOnClickListener: SetOnClickClockListener? = null
    fun setOnClickClockListener(setOnClickClockListener: SetOnClickClockListener){
        this.setOnClickListener = setOnClickClockListener
    }


    init {
        loadData()
        dialogBinding.timeZoneList.layoutManager = LinearLayoutManager(context)
        dialogBinding.timeZoneList.adapter = adapter

        adapter.setOnClick(object : TimeZoneAdapter.SetOnClickListener {
            override fun onClick(model: MyTimeZone) {
                setOnClickListener!!.setSaveClocks(model)
            }

        })

        setView(dialogBinding.root)
    }

    private fun loadData() {
        data.addAll(getAllTimeZones())
        adapter.addData(data)
    }

    interface SetOnClickClockListener {
        fun setSaveClocks(model: MyTimeZone)
    }


}