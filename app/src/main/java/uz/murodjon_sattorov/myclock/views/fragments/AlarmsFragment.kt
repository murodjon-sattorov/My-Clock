package uz.murodjon_sattorov.myclock.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rm.rmswitch.RMSwitch
import uz.murodjon_sattorov.myclock.core.adapters.AlarmAdapter
import uz.murodjon_sattorov.myclock.core.helpers.AlarmBroadcast
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.databinding.FragmentAlarmsBinding
import uz.murodjon_sattorov.myclock.viewmodel.AlarmViewModel
import uz.murodjon_sattorov.myclock.views.dialogs.AlarmSetDialog
import uz.murodjon_sattorov.myclock.views.dialogs.AlarmUpdateDialog
import uz.murodjon_sattorov.myclock.views.dialogs.GetCheckVibrateAndLabel


class AlarmsFragment : Fragment() {

    private var _binding: FragmentAlarmsBinding? = null;
    private val binding get() = _binding!!

    private var adapter = AlarmAdapter()
    private lateinit var alarmViewModel: AlarmViewModel

    private var alarmBroadcast: AlarmBroadcast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlarmsBinding.inflate(inflater, container, false)

        binding.alarmList.adapter = adapter
        binding.alarmList.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener {
            openDialog()
        }

        alarmBroadcast = AlarmBroadcast()
        alarmBroadcast!!.createNotificationChannel(requireContext())

        loadAlarms()

        adapterItemsClicked()

        return binding.root
    }

    private fun openDialog() {
        val dialog = AlarmSetDialog(requireContext())
        dialog.setTitle("Select time")
        dialog.setCancelable(false)
        dialog.setOnCheckVibrateAndLabel(object : GetCheckVibrateAndLabel {
            override fun getVibrateAndLabel(
                requestCode: Int,
                hour: String,
                minutes: String,
                vibrate: Boolean,
                repeat: Boolean,
                label: String,
                monday: Boolean,
                tuesday: Boolean,
                wednesday: Boolean,
                thursday: Boolean,
                friday: Boolean,
                saturday: Boolean,
                sunday: Boolean
            ) {
                alarmViewModel = ViewModelProvider(this@AlarmsFragment)[AlarmViewModel::class.java]
                val alarmModel = AlarmModel(
                    requestCode,
                    Integer.parseInt(hour),
                    Integer.parseInt(minutes),
                    label,
                    vibrate,
                    repeat,
                    monday,
                    tuesday,
                    wednesday,
                    thursday,
                    friday,
                    saturday,
                    sunday
                )
                alarmBroadcast?.setAlarm(
                    requireContext(),
                    Integer.parseInt(hour),
                    Integer.parseInt(minutes),
                    requestCode
                )
                alarmViewModel.addAlarm(alarmModel)
                loadAlarms()
            }

        })

        dialog.show()
    }

    private fun loadAlarms() {
        alarmViewModel = ViewModelProvider(this)[AlarmViewModel::class.java]
        alarmViewModel.readAllAlarm.observe(viewLifecycleOwner, {
            if (it==null){
                
            }else{
                adapter.addData(it)
            }
        })
    }

    private fun adapterItemsClicked() {
        adapter.setOnClick(object : AlarmAdapter.SetOnClickListener {
            override fun onUpdateClick(model: AlarmModel) {
                val dialog = AlarmUpdateDialog(
                    requireContext(), model
                )
                dialog.setTitle("Select time")
                dialog.setCancelable(true)
                dialog.setOnCheckVibrateAndLabel(object : GetCheckVibrateAndLabel {
                    override fun getVibrateAndLabel(
                        requestCode: Int,
                        hour: String,
                        minutes: String,
                        vibrate: Boolean,
                        repeat: Boolean,
                        label: String,
                        monday: Boolean,
                        tuesday: Boolean,
                        wednesday: Boolean,
                        thursday: Boolean,
                        friday: Boolean,
                        saturday: Boolean,
                        sunday: Boolean
                    ) {
                        alarmBroadcast?.cancelAlarm(requireContext(), requestCode)
                        alarmViewModel =
                            ViewModelProvider(this@AlarmsFragment)[AlarmViewModel::class.java]
                        val alarmModel = AlarmModel(
                            requestCode,
                            Integer.parseInt(hour),
                            Integer.parseInt(minutes),
                            label,
                            vibrate,
                            repeat,
                            monday,
                            tuesday,
                            wednesday,
                            thursday,
                            friday,
                            saturday,
                            sunday
                        )
                        alarmBroadcast?.setAlarm(
                            requireContext(),
                            Integer.parseInt(hour),
                            Integer.parseInt(minutes),
                            id
                        )
                        alarmViewModel.updateAlarm(alarmModel)
                        loadAlarms()
                    }

                })

                dialog.show()
            }

            override fun onRepeatClick(model: AlarmModel, view: RMSwitch) {
                model.repeat = !view.isChecked

                alarmViewModel.updateAlarm(model)

                if (model.repeat) {
                    alarmBroadcast?.setAlarm(requireContext(), model.hour, model.minute, model.id)
                } else {
                    alarmBroadcast?.cancelAlarm(requireContext(), model.id)
                }
                loadAlarms()
            }

            override fun onDeleteSwipe(model: AlarmModel) {
                deleteAlarm(model)
            }

        })
    }

    private fun deleteAlarm(model: AlarmModel) {
        alarmViewModel = ViewModelProvider(this@AlarmsFragment)[AlarmViewModel::class.java]
        alarmBroadcast?.cancelAlarm(requireContext(), model.id)
        alarmViewModel.deleteAlarm(model)
        loadAlarms()
    }


}