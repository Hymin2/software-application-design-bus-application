package ac.kr.tukorea.bus_application.Data.DB.Database

import ac.kr.tukorea.bus_application.Data.DB.DAO.*
import ac.kr.tukorea.bus_application.Data.DB.Entity.*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =
    [BookmarkEntity::class,
    AlarmGettingOffEntity::class,
    AlarmRidingEntity::class
    ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDAO
    abstract fun alarmGettingOffDao() : AlarmGettingOffDAO
    abstract fun alarmRidingDao() : AlarmRidingDAO

    companion object{
        fun getInstance(context: Context) : AppDatabase{
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "busappdb").fallbackToDestructiveMigration().build()
        }
    }
}