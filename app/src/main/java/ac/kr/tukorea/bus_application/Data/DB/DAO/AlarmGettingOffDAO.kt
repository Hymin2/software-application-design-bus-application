package ac.kr.tukorea.bus_application.Data.DB.DAO

import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmGettingOffEntity
import ac.kr.tukorea.bus_application.Data.DB.Entity.BookmarkEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmGettingOffDAO {
    @Query("select * from bookmark_table b")
    fun getAllBookmark() : AlarmGettingOffEntity

    @Query("select exists(select b.id from bookmark_table b where route_id = :routeid and stop_id = :stopid)")
    fun isExistsBookmarkByRouteIdAndStopId(routeid : Int, stopid : Int) : Boolean

    @Insert
    fun insertBookmark(vararg bookmark : BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmark : BookmarkEntity)
}