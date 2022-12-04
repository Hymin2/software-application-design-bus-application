package com.example.bus_application.Activity

import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.RouteDetailsListAdapter
import com.example.bus_application.Adapter.SearchRouteListAdapter
import com.example.bus_application.Client.RetrofitClient
import com.example.bus_application.DTO.RouteDetailsStopDTO
import com.example.bus_application.DTO.SearchRouteDTO
import com.example.bus_application.Service.ApiService
import com.example.bus_application.databinding.ActivityRouteDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RouteDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRouteDetailsBinding
    var turnPosition : Int? = null
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var item = intent.getSerializableExtra("item") as SearchRouteDTO

        binding.run {
            routeBusName.text = item.name
            routeStartEndStop.text = item.st_sta_nm + " - " + item.ed_sta_nm
            routeStartEndTime.text = item.up_first_time + " ~ " + item.up_last_time + " | " + item.peek + " ~ " + item.npeek + "분"

            rvRouteList.apply{
                this.layoutManager = LinearLayoutManager(context)
            }

            getRouteDetails(item.id)

            routeStartStop.setOnClickListener {
                rvRouteList.smoothScrollToPosition(0)
            }

            routeEndStop.setOnClickListener {
                rvRouteList.smoothScrollToPosition(turnPosition!!)
            }
        }

    }

    fun getRouteDetails(route_id : Int){
        apiService.getRouteDetailsList(route_id).enqueue(object : Callback<ArrayList<RouteDetailsStopDTO>> {
            override fun onResponse(
                call: Call<ArrayList<RouteDetailsStopDTO>>,
                response: Response<ArrayList<RouteDetailsStopDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    binding.rvRouteList.adapter = RouteDetailsListAdapter(response.body()!!)

                    for (i : Int in 1 until response.body()!!.size){
                        if(!response.body()!!.get(i).updown.equals(response.body()!!.get(i - 1).updown)){
                            turnPosition = i
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
}