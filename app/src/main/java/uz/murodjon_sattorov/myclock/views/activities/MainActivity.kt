package uz.murodjon_sattorov.myclock.views.activities

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.murodjon_sattorov.myclock.R
import uz.murodjon_sattorov.myclock.core.adapters.MainPagerAdapter
import uz.murodjon_sattorov.myclock.core.helpers.CHANNEL_TIME_FINISHED
import uz.murodjon_sattorov.myclock.databinding.ActivityMainBinding
import uz.murodjon_sattorov.myclock.views.fragments.AlarmsFragment
import uz.murodjon_sattorov.myclock.views.fragments.ClocksFragment
import uz.murodjon_sattorov.myclock.views.fragments.StopwatchFragment
import uz.murodjon_sattorov.myclock.views.fragments.TimersFragment


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private var adapter: MainPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        adapter = MainPagerAdapter(supportFragmentManager)

        loadFragments()

        mainBinding.viewPagers.adapter = adapter
        mainBinding.tabLayout.setupWithViewPager(mainBinding.viewPagers)

        loadIcons()

    }

    private fun loadFragments() {
        adapter!!.addPagerFragment(AlarmsFragment(), "")
        adapter!!.addPagerFragment(ClocksFragment(), "")
        adapter!!.addPagerFragment(StopwatchFragment(), "")
        adapter!!.addPagerFragment(TimersFragment(), "")
    }

    private fun loadIcons() {
        mainBinding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_round_access_alarm_24)
        mainBinding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_round_access_clock_24)
        mainBinding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_countdown_timer_24)
        mainBinding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_round_hourglass_timer_24)
    }

    companion object {
        fun getDismissIntent(notificationId: Int, context: Context): PendingIntent? {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(CHANNEL_TIME_FINISHED, notificationId)
            return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        }
    }

}