package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.databinding.ItemRecyclerBookmarkBusBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookmarkBusAdapter : RecyclerView.Adapter<BookmarkBusAdapter.MyBusList>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBusList {
        val view = ItemRecyclerBookmarkBusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyBusList(view)
    }

    override fun onBindViewHolder(holder: MyBusList, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class MyBusList(private val binding: ItemRecyclerBookmarkBusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){


            binding.textRemainTime.text = "남은 시간 $pos 분"
        }
    }

}