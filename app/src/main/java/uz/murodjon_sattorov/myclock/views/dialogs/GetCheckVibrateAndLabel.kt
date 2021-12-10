package uz.murodjon_sattorov.myclock.views.dialogs

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/25/2021
 * @project My Clock
 */
interface GetCheckVibrateAndLabel {
    fun getVibrateAndLabel(
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
    )
}