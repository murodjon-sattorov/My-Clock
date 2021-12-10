package uz.murodjon_sattorov.myclock.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import uz.murodjon_sattorov.myclock.core.adapters.ClocksAdapter
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone
import uz.murodjon_sattorov.myclock.databinding.FragmentClocksBinding
import uz.murodjon_sattorov.myclock.viewmodel.AlarmViewModel
import uz.murodjon_sattorov.myclock.views.dialogs.TimeZoneDialog
import java.text.SimpleDateFormat
import java.util.*

class ClocksFragment : Fragment() {

    private var _binding: FragmentClocksBinding? = null
    private val binding get() = _binding!!

    private var adapter = ClocksAdapter()
    private lateinit var clockViewModel: AlarmViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentClocksBinding.inflate(inflater, container, false)

        binding.realDate.text =
            "Current: " + SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                Date()
            )

        binding.clocksList.adapter = adapter
        binding.clocksList.layoutManager = LinearLayoutManager(requireContext())


        binding.floatingActionButton.setOnClickListener {
            loadClocksDialog()
        }

        loadAlarms()

        return binding.root
    }

    private fun loadClocksDialog() {
        val dialog = TimeZoneDialog(requireContext())
        dialog.setTitle("Select country")
        dialog.setCancelable(true)
        dialog.setOnClickClockListener(object : TimeZoneDialog.SetOnClickClockListener {
            override fun setSaveClocks(model: MyTimeZone) {
                clockViewModel = ViewModelProvider(this@ClocksFragment)[AlarmViewModel::class.java]
                val clockModel = MyTimeZone(model.id, model.title, model.zoneName)
                clockViewModel.addClock(clockModel)
                dialog.dismiss()
                dialog.cancel()
                loadAlarms()
            }

        })

        dialog.show()
    }

    private fun loadAlarms() {
        clockViewModel = ViewModelProvider(this)[AlarmViewModel::class.java]
        clockViewModel.readAllClocks.observe(viewLifecycleOwner, {
            adapter.addData(it)
        })
    }

}