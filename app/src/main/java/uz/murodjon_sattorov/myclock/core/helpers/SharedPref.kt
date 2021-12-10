package uz.murodjon_sattorov.myclock.core.helpers

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/25/2021
 * @project My Clock
 */
object SharedPref {

    private var sharedPref: SharedPreferences? = null

    fun saveRequestCode(context: Context, code: Int) {
        sharedPref = context.getSharedPreferences("save_code", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref!!.edit()
        editor.putInt("$code", code)
        editor.apply()
        editor.commit()

    }

    fun readRequestCode(context: Context): Int {
        sharedPref = context.getSharedPreferences("save_code", Context.MODE_PRIVATE)
        return sharedPref!!.getInt("code", 0)
    }

}