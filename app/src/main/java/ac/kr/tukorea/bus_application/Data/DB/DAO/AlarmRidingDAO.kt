package ac.kr.tukorea.bus_application.Data.DB.DAO

import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmRidingEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmRidingDAO {
    @Query("select * from alarm_riding_table ar")
    fun getAlarmRiding() : AlarmRidingEntity

    @Query("select exists(select ar.id from alarm_riding_table ar)")
    fun isEmptyAlarmRiding() : Boolean

    @Insert
    fun insertAlarmRiding(vararg alarmRidingEntity: AlarmRidingEntity)

    @Delete
    fun deleteAlarmRiding(alarmRidingEntity: AlarmRidingEntity)
}