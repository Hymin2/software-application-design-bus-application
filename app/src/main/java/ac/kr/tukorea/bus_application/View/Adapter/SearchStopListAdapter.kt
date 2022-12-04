package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmGettingOffEntity
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerSearchStopBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchStopListAdapter(private val items : MutableList<SearchStopDTO>, private val db : AppDatabase) : RecyclerView.Adapter<SearchStopListAdapter.MyStopList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStopList {
        val view = ItemRecyclerSearchStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyStopList(view)
    }

    override fun onBindViewHolder(holder: MyStopList, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyStopList(private val binding: ItemRecyclerSearchStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            val item = items[pos]

            binding.stopName.text = item.name
            binding.stopRegion.text = item.region_name

            binding.stopBookmarkCb.setOnClickListener {
                when(binding.stopBookmarkCb.isChecked){
                    true -> {
                        val insert = AlarmGettingOffEntity()
                        Thread(Runnable {
                            db.alarmGettingOffDao().insertAlarmGettingOff()
                        }
                        ).start()
                    }

                    false -> {

                    }
                }
            }
        }
    }
}