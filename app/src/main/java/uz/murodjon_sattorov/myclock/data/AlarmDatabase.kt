package uz.murodjon_sattorov.myclock.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import uz.murodjon_sattorov.myclock.core.models.AlarmModel
import uz.murodjon_sattorov.myclock.core.models.MyTimeZone

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 11/20/2021
 * @project My Clock
 */
@Database(entities = [AlarmModel::class, MyTimeZone::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    companion object {
        private var db: AlarmDatabase? = null

        fun getInstance(context: Context): AlarmDatabase {
            if (db == null) {
                synchronized(AlarmDatabase::class) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            context.applicationContext,
                            AlarmDatabase::class.java,
                            "clock_db"
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                }
                            })
                            .build()
                    }
                }
            }
            return db!!
        }

    }

}