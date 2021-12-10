package uz.murodjon_sattorov.myclock.core.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/19/2021
 * @project My Clock
 */
class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    var fragmentArrayList = ArrayList<Fragment>()
    var stringArrayList = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return fragmentArrayList[position]
    }

    override fun getCount(): Int {
        return stringArrayList.size
    }

    fun addPagerFragment(fragment: Fragment, s: String) {
        fragmentArrayList.add(fragment)
        stringArrayList.add(s)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringArrayList[position]
    }


}