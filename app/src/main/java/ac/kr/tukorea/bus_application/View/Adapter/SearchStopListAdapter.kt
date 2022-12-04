package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmGettingOffEntity
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerSearchStopBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SearchStopListAdapter(private val items : ArrayList<SearchStopDTO>,private val checked : ArrayList<Boolean>, private val db : AppDatabase) : RecyclerView.Adapter<SearchStopListAdapter.MyStopList>() {

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
            binding.stopBookmarkCb.isChecked = checked[pos]

            binding.stopBookmarkCb.setOnClickListener {
                when(binding.stopBookmarkCb.isChecked){
                    true -> {
                        Thread(Runnable {
                            if(db.alarmGettingOffDao().isEmptyAlarmGettingOff()){
                                val insert = AlarmGettingOffEntity(0, item.id, item.name, item.gps_x, item.gps_y)
                                db.alarmGettingOffDao().insertAlarmGettingOff(insert)
                            }
                            else{
                                binding.stopBookmarkCb.isChecked = false
                            }
                        }).start()
                    }
                    false -> {
                        Thread(Runnable {
                            val delete = db.alarmGettingOffDao().getAlarmGetiingOff()
                            db.alarmGettingOffDao().deleteAlarmGettingOff(delete)
                        }).start()
                    }
                }
            }

        }
    }
}