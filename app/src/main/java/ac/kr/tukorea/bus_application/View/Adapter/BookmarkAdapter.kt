package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.databinding.ItemRecyclerBookmarkStopBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.MyStopList>(){
    private val list = listOf(1,2,3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStopList {
        val view = ItemRecyclerBookmarkStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyStopList(view)
    }

    inner class MyStopList(private val binding: ItemRecyclerBookmarkStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.textStopName.text = "버스정류장 $pos 번"
            binding.textBusNum.text = "버스 번호 $pos 번"
            binding.textBusTime.text = "남은 시간 $pos 번"
        }
    }

    override fun onBindViewHolder(holder: MyStopList, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}