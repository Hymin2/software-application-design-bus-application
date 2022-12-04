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
    fun getAllBookmark() : LiveData<List<BookmarkEntity>>

    @Query("select exists(select b.id from bookmark_table b where b.route_id = :routeid and b.stop_id = :stopid)")
    fun isExistsBookmarkByRouteId(routeid : Int, stopid : Int) : Boolean

    @Insert
    fun insertBookmark(vararg bookmark : BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmark : BookmarkEntity)
}