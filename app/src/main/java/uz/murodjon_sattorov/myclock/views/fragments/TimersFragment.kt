package uz.murodjon_sattorov.myclock.views.fragments

import android.annotation.SuppressLint
import android.app.Notification
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.helpers.*
import uz.murodjon_sattorov.myclock.databinding.FragmentTimersBinding

class TimersFragment : Fragment() {

    private var _binding: FragmentTimersBinding? = null
    private val binding get() = _binding!!

    private var pause = false

    private var countDownTimer: CountDownTimer? = null

    private var notificationManager: NotificationManagerCompat? = null

    private var timerNotification:TimerNotification? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentTimersBinding.inflate(layoutInflater)

        notificationManager = NotificationManagerCompat.from(requireContext())
        timerNotification = TimerNotification(requireContext())
        timerNotification!!.createNotificationChannels()

        binding.startButton.setOnClickListener {
            ALL_TIMER_SECONDS =
                (binding.alarmTimePickerSecond.value + binding.alarmTimePickerMinute.value * 60 + binding.alarmTimePickerHour.value * 3600 + 1) * 1000
            setTotalTime()
            startBtnClick()
        }
        binding.stopButton.setOnClickListener {
            stopBtnClick()
        }
        binding.pauseAndPlayButton.setOnClickListener {
            pausePlayClick()
        }

    }

    private fun startBtnClick() {
        binding.hmsPicker.visibility = View.GONE
        binding.startButton.visibility = View.GONE
        binding.progressView.visibility = View.VISIBLE
        binding.timeSteps.visibility = View.VISIBLE
        binding.stopButton.visibility = View.VISIBLE
        binding.pauseAndPlayButton.visibility = View.VISIBLE
        startTimer()
        binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_pause_24)
    }

    private fun stopBtnClick() {
        countDownTimer?.cancel()
        binding.progressView.visibility = View.GONE
        binding.timeSteps.visibility = View.GONE
        binding.stopButton.visibility = View.GONE
        binding.pauseAndPlayButton.visibility = View.GONE
        binding.hmsPicker.visibility = View.VISIBLE
        binding.startButton.visibility = View.VISIBLE
        pause = false
    }

    private fun pausePlayClick() {
        pause = if (pause) {
            binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_pause_24)
            false
        } else {
            binding.pauseAndPlayButton.setImageResource(R.drawable.ic_round_play_arrow_24)
            true
        }
    }

    private fun startTimer() {
        var startTime = ALL_TIMER_SECONDS
        binding.progressView.setMaxProgressOuterView(startTime - 1000)
        countDownTimer = object : CountDownTimer(startTime.toLong(), COUNT_TIMER) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                startTime -= 1000
                if (startTime == 0) {
                    binding.progressView.setOuterProgress(1)
                } else binding.progressView.setOuterProgress(startTime)
                val hour = (startTime / 3600) / 1000
                val minute = ((startTime / 60) / 1000) % 60
                val second = startTime / 1000 % 60
                val time = "${String.format("%02d", hour)}:${
                    String.format(
                        "%02d",
                        minute
                    )
                }:${String.format("%02d", second)}"
                binding.timeSteps.text = time
                timerNotification?.runNotification(time, notificationManager!!)
            }

            override fun onFinish() {
                timerNotification?.finishNotification(notificationManager!!)
                stopBtnClick()
            }
        }
        countDownTimer?.start()
    }

    private fun setTotalTime() {
        val hour = ((ALL_TIMER_SECONDS - 1000) / 3600) / 1000
        val minute = (((ALL_TIMER_SECONDS - 1000) / 60) / 1000) % 60
        val second = (ALL_TIMER_SECONDS - 1000) / 1000 % 60
        var textHour = ""
        var textMin = ""
        var textSec = ""
        Log.d("TTT", "setTotalTime: $ALL_TIMER_SECONDS $hour $minute $second")
        if (hour > 1 || minute > 1 || second > 1) {
            textHour = if (hour > 1) "$hour hours " else if (hour == 0) "" else "$hour hour "
            textMin = if (minute > 1) "$minute minutes " else if (minute == 0) "" else "$minute second "
            textSec = if (second > 1) "$second seconds " else if (second == 0) "" else "$second second "
        } else {
            textHour = "$hour hour "
            textMin = "$minute second "
            textSec = "$second second "
        }
        TOTAL_TIME = textHour + textMin + textSec
    }


}