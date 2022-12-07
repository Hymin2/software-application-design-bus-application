package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.Remote.Client.RetrofitClient
import ac.kr.tukorea.bus_application.Data.Remote.DTO.AllBusDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.RouteDetailsStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.Data.Remote.Service.ApiService
import ac.kr.tukorea.bus_application.View.Adapter.RouteDetailsListAdapter
import ac.kr.tukorea.bus_application.View.Adapter.SearchRouteListAdapter
import ac.kr.tukorea.bus_application.View.Adapter.SearchStopListAdapter
import ac.kr.tukorea.bus_application.databinding.ActivityRouteDetailsBinding
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RouteDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRouteDetailsBinding
    var turnPosition : Int? = null
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)
    private lateinit var db : AppDatabase
    var all_bus = arrayListOf<AllBusDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(binding.root.context)

        var item : SearchRouteDTO = intent.getSerializableExtra("item") as SearchRouteDTO

        binding.run {
            routeBusName.text = item!!.name
            routeStartEndStop.text = item!!.st_sta_nm + " - " + item!!.ed_sta_nm
            routeStartEndTime.text = item!!.up_first_time + " ~ " + item!!.up_last_time + " | " + item!!.peek + " ~ " + item!!.npeek + "분"

            rvRouteList.apply{
                this.layoutManager = LinearLayoutManager(context)
            }

            getRouteDetails(item)

            routeStartStop.apply {
                this.text = item!!.ed_sta_nm + " 방면"
                this.setOnClickListener {
                    rvRouteList.smoothScrollToPosition(0)
                }
            }

            routeEndStop.apply {
                this.text = item!!.st_sta_nm + " 방면"
                this.setOnClickListener {
                    rvRouteList.smoothScrollToPosition(turnPosition!!)
                }
            }
        }

    }

    fun getRouteDetails(item : SearchRouteDTO){
        apiService.getRouteDetailsList(item.id).enqueue(object : Callback<ArrayList<RouteDetailsStopDTO>> {
            override fun onResponse(
                call: Call<ArrayList<RouteDetailsStopDTO>>,
                response: Response<ArrayList<RouteDetailsStopDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    Thread(Runnable {
                        var routeid : Int?
                        var stopid : Int?
                        var checked_star = arrayListOf<Boolean>()
                        var checked_alarm = arrayListOf<Boolean>()
                        var body = response.body()!!

                        getAllBus(item.id)

                        if(!db.alarmRidingDao().isEmptyAlarmRiding()) {
                            var data = db.alarmRidingDao().getAlarmRiding()

                            routeid = data.route_id
                            stopid = data.stop_id

                            for (i: Int in 0 until body!!.size) {
                                if (response.body()!![i].stop_id == stopid && item.id == routeid)
                                    checked_alarm!!.add(i, true)
                                else
                                    checked_alarm!!.add(i, false)
                            }
                        }

                        else{
                            for (i: Int in 0 until body!!.size) {
                                checked_alarm!!.add(i, false)
                            }
                        }

                        if(!db.bookmarkDao().isEmptyBookmark()) {
                            for (i: Int in 0 until body!!.size) {
                                if (db.bookmarkDao().isExistsBookmarkByRouteId(item.id, response.body()!![i].stop_id))
                                    checked_star!!.add(i, true)
                                else
                                    checked_star!!.add(i, false)
                            }
                        }
                        else{
                            for (i: Int in 0 until body!!.size) {
                                checked_star!!.add(i, false)
                            }
                        }

                        runOnUiThread {
                            binding.rvRouteList.adapter = RouteDetailsListAdapter(body,checked_star, checked_alarm, item, db, all_bus!!)
                        }
                    }).start()

                    for (i : Int in 1 until response.body()!!.size){
                        if(!response.body()!!.get(i).updown.equals(response.body()!!.get(i - 1).updown)){
                            turnPosition = i
                            break
                        }
                    }
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<RouteDetailsStopDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun getAllBus(routeid : Int){
        apiService.getAllBus(routeid).enqueue(object : Callback<ArrayList<AllBusDTO>> {
            override fun onResponse(
                call: Call<ArrayList<AllBusDTO>>,
                response: Response<ArrayList<AllBusDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    all_bus = response.body()!!
                }
            }

            override fun onFailure(call: Call<ArrayList<AllBusDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }
}