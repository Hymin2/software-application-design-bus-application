package ac.kr.tukorea.bus_application.Data.DB.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_riding_table")
data class AlarmRidingEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val stop_id : Int,
    val stop_name : String,
    val route_id : Int,
    val route_name : String,
    val gps_x : Double,
    val gps_y : Double
)
