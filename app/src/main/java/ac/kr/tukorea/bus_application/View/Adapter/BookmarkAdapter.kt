package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.DB.Entity.BookmarkEntity
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerBookmarkStopBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.MyStopList>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStopList {
        val view = ItemRecyclerBookmarkStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyStopList(view)
    }

    override fun onBindViewHolder(holder: MyStopList, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class MyStopList(private val binding: ItemRecyclerBookmarkStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.textStopDirection.text = "정류장 방면 $pos 번"


            binding.rvBus.apply {
                layoutManager = LinearLayoutManager(binding.rvBus.context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }
}