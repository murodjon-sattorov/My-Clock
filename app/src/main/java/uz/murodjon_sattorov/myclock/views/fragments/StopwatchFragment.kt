package uz.murodjon_sattorov.myclock.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.StopwatchAdapter
import uz.murodjon_sattorov.myclock.core.models.StopwatchModel
import uz.murodjon_sattorov.myclock.databinding.FragmentStopwatchsBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class StopwatchFragment : Fragment() {

    private var _binding: FragmentStopwatchsBinding? = null
    private val binding get() = _binding!!

    private var adapter = StopwatchAdapter()
    private var data = ArrayList<StopwatchModel>()

    private var handler = Handler()
    private lateinit var runnable: Runnable

    private var stop = false
    private var pause = false

    private var milliSecond = 0L
    private var start = 0L
    private var buff = 0L
    private var update = 0L
    private var sec = 0
    private var min = 0
    private var milliSec = 0
    private var count = 0;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = FragmentStopwatchsBinding.inflate(layoutInflater)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        layoutManager.stackFromEnd = true
        binding.lapList.layoutManager = layoutManager


        runnable = Runnable {
            milliSecond = SystemClock.uptimeMillis() - start
            update = buff + milliSecond
            sec = (update / 1000).toInt()
            min = sec / 60
            sec %= 60
            milliSec = (update % 100).toInt()
            binding.stopwatchText.text = String.format("%02d", min) + ":" +
                    String.format("%02d", sec) + ":" + String.format("%02d", milliSec)
            handler.postDelayed(runnable, 60)
        }

        binding.startButton.setOnClickListener {
            binding.startButton.visibility = View.GONE
            binding.lapAndStopButton.visibility = View.VISIBLE
            binding.pauseAndPlayButton.visibility = View.VISIBLE
            start = SystemClock.uptimeMillis()
            handler.postDelayed(runnable, 0)
            binding.stopwatchText.start()

        }

        binding.lapAndStopButton.setOnClickListener {
            if (stop) {
                milliSecond = 0
                start = 0
                buff = 0
                update = 0
                sec = 0
                min = 0
                milliSec = 0
                count = 0
                binding.stopwatchText.text = "00:00:00"
                data.clear()
                adapter.clear()
                binding.startButton.visibility = View.VISIBLE
                binding.lapAndStopButton.visibility = View.GONE
                binding.pauseAndPlayButton.visibility = View.GONE
                binding.lapAndStopButton.setImageResource(R.drawable.ic_round_flag_24)
                binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_pause_24)
                stop = false
                pause = false

            } else {

                loadData(binding.stopwatchText.text.toString())
                adapter.addData(data)
                binding.lapList.adapter = adapter

            }
        }

        binding.pauseAndPlayButton.setOnClickListener {
            if (pause) {
                start = SystemClock.uptimeMillis()
                handler.postDelayed(runnable, 0)
                binding.stopwatchText.start()
                binding.lapAndStopButton.setImageResource(R.drawable.ic_round_flag_24)
                binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_pause_24)
                stop = false
                pause = false
            } else {
                buff += milliSecond
                handler.removeCallbacks(runnable)
                binding.stopwatchText.stop()
                binding.lapAndStopButton.setImageResource(R.drawable.ic_round_stop_24)
                binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_play_arrow_24)
                stop = true
                pause = true
            }
        }
    }

    private fun loadData(time: String) {
        count++
        data.add(StopwatchModel(count, time))
    }


}