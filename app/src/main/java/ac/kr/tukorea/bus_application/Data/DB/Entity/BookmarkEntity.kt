package ac.kr.tukorea.bus_application.Data.DB.Entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val route_id : Int,
    val route_name : String,
    val stop_id : Int,
    val stop_name : String,
    val gps_x : Double,
    val gps_y : Double
)
