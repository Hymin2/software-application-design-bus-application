package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.DB.Entity.BookmarkEntity
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerBookmarkStopBinding
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BookmarkAdapter(private val items : List<BookmarkEntity>, private val db : AppDatabase) : RecyclerView.Adapter<BookmarkAdapter.MyStopList>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.MyStopList {
        val view = ItemRecyclerBookmarkStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyStopList(view)
    }

    override fun onBindViewHolder(holder: MyStopList, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyStopList(private val binding: ItemRecyclerBookmarkStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            val item = items[pos]

            binding.textStopName.text = item.stop_name
            binding.textBusNum.text = item.route_name
            binding.textBusTime.text = "남은 시간 $pos 분"

            binding.bookmarkDeleteBtn.setOnClickListener {
                items.drop(pos)
                Thread(Runnable {
                    db.bookmarkDao().deleteBookmark(item)
                }).start()

                notifyItemRemoved(pos)

                val intent = (binding.root.context as Activity).intent
                (binding.root.context as Activity).finish() //현재 액티비티 종료 실시
                (binding.root.context as Activity).overridePendingTransition(0, 0) //효과 없애기
                (binding.root.context as Activity).startActivity(intent) //현재 액티비티 재실행 실시
                (binding.root.context as Activity).overridePendingTransition(0, 0) //효과 없애기


            }
        }
    }
}