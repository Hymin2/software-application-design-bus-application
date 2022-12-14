package ac.kr.tukorea.bus_application.Data.DB.DAO

import ac.kr.tukorea.bus_application.Data.DB.Entity.BookmarkEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDAO {
    @Query("select * from bookmark_table")
    fun getAllBookmark() : List<BookmarkEntity>

    @Query("select exists(select b.id from bookmark_table b where b.route_id = :routeid and b.stop_id = :stopid)")
    fun isExistsBookmarkByRouteId(routeid : Int, stopid : Int) : Boolean

    @Query("select not exists(select b.id from bookmark_table b)")
    fun isEmptyBookmark() : Boolean

    @Query("select * from bookmark_table b where b.route_id = :routeid and b.stop_id = :stopid")
    fun findByRouteIdAndStopId(routeid : Int, stopid : Int) : BookmarkEntity

    @Insert
    fun insertBookmark(vararg bookmark : BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmark : BookmarkEntity)
}