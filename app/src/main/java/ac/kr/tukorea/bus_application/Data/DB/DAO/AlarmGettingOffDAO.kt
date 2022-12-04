package ac.kr.tukorea.bus_application.Data.DB.DAO

import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmGettingOffEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmGettingOffDAO {
    @Query("select * from alarm_get_off_table ag")
    fun getAlarmGetiingOff() : AlarmGettingOffEntity

    @Query("select not exists(select ag.id from alarm_get_off_table ag)")
    fun isEmptyAlarmGettingOff() : Boolean

    @Insert
    fun insertAlarmGettingOff(vararg alarmGettingOffEntity: AlarmGettingOffEntity)

    @Delete
    fun deleteAlarmGettingOff(alarmGettingOffEntity: AlarmGettingOffEntity)
}