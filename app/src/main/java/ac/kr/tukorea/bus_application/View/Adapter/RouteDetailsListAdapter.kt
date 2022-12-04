package ac.kr.tukorea.bus_application.View.Adapter

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmGettingOffEntity
import ac.kr.tukorea.bus_application.Data.DB.Entity.AlarmRidingEntity
import ac.kr.tukorea.bus_application.Data.DB.Entity.BookmarkEntity
import ac.kr.tukorea.bus_application.Data.Remote.DTO.RouteDetailsStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.View.Activity.KakaoMapActivity
import ac.kr.tukorea.bus_application.View.Activity.RouteDetailsActivity
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerRouteBinding
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RouteDetailsListAdapter(private val items : ArrayList<RouteDetailsStopDTO>,
                              private val checked_star : ArrayList<Boolean>,
                              private val checked_alarm : ArrayList<Boolean>,
                              private val route_item : SearchRouteDTO,
                              private val db : AppDatabase)
                            : RecyclerView.Adapter<RouteDetailsListAdapter.MyRouteDetails>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRouteDetails {
        val view = ItemRecyclerRouteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyRouteDetails(view)
    }

    override fun onBindViewHolder(holder: MyRouteDetails, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyRouteDetails(private val binding: ItemRecyclerRouteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            val item = items.get(pos)

            binding.routeStarCb.isChecked = checked_star[pos]
            binding.routeAlarmCb.isChecked = checked_alarm[pos]

            binding.routeStarCb.setOnClickListener {
                when(binding.routeStarCb.isChecked){
                    true -> {
                        Thread(Runnable {
                            val insert = BookmarkEntity(0, route_item.id, route_item.name, item.stop_id, item.stop_name)
                            db.bookmarkDao().insertBookmark(insert)
                            checked_star[pos] = true
                        }).start()
                    }
                    false -> {
                        Thread(Runnable {
                            val delete = db.bookmarkDao().findByRouteIdAndStopId(route_item.id, item.stop_id)
                            db.bookmarkDao().deleteBookmark(delete)
                            checked_star[pos] = false
                        }).start()
                    }
                }
            }

            var activity = RouteDetailsActivity()

            binding.routeAlarmCb.setOnClickListener {
                when(binding.routeAlarmCb.isChecked){
                    true -> {
                        Thread(Runnable {
                            if(db.alarmRidingDao().isEmptyAlarmRiding()){
                                val insert = AlarmRidingEntity(0, route_item.id, route_item.name, item.stop_id, item.stop_name)
                                db.alarmRidingDao().insertAlarmRiding(insert)
                                checked_alarm[pos] = true
                            }
                            else{
                                activity.runOnUiThread {
                                    binding.routeAlarmCb.isChecked = false
                                    checked_alarm[pos] = false
                                    Toast.makeText(binding.root.context, "승차 알람이 이미 설정되었습니다.",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }).start()
                    }
                    false -> {
                        Thread(Runnable {
                            val delete = db.alarmRidingDao().getAlarmRiding()
                            db.alarmRidingDao().deleteAlarmRiding(delete)
                            checked_alarm[pos] = false
                        }).start()
                    }
                }
            }

            when(item.stop_order){
                1 -> binding.upView.visibility = View.INVISIBLE
                items.size -> binding.downView.visibility = View.INVISIBLE
                else -> {
                    binding.upView.visibility = View.VISIBLE
                    binding.downView.visibility = View.VISIBLE
                }
            }

            if(item.stop_order != 1 && !item.updown.equals(items.get(item.stop_order - 2).updown)){
                binding.routeTurn.visibility = View.VISIBLE
                binding.arrowImage.visibility = View.INVISIBLE
            }
            else{
                binding.routeTurn.visibility = View.INVISIBLE
                binding.arrowImage.visibility = View.VISIBLE
            }

            binding.stopName.text = item.stop_name
            binding.mobileNo.text = item.mobile_no

            binding.root.setOnClickListener {
                var intent = Intent(binding.root.context, KakaoMapActivity::class.java)
                binding.root.context.startActivity(intent)
                (binding.root.context as Activity).finish()

            }
        }
    }
}