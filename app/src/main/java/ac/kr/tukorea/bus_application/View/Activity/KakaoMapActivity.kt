package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.Remote.Client.RetrofitClient
import ac.kr.tukorea.bus_application.Data.Remote.DTO.AllBusDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.Service.ApiService
import ac.kr.tukorea.bus_application.databinding.ActivityKakaoMapBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KakaoMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMapBinding
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)
    var gps_x : Double? = null
    var gps_y : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var stop_id = getIntent().getIntExtra("stop_id", 0)
        var route_id = intent.getIntExtra("route_id", 0)
        var route_name = intent.getStringExtra("route_name")
        var stop_order = intent.getIntExtra("stop_order",0)

        getStopInfo(stop_id)
        getBusGPS(route_id, stop_order, route_name!!)

        binding.textBusNumMap.text = route_name!!

        Log.d("stopoid",stop_id.toString())
    }

    fun getStopInfo(id : Int) {
        apiService.getStopById(id).enqueue(object : Callback<SearchStopDTO> {
            override fun onResponse(
                call: Call<SearchStopDTO>,
                response: Response<SearchStopDTO>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    var searchStopDTO = response.body()!!
                    gps_x = searchStopDTO.gps_x
                    gps_y = searchStopDTO.gps_y

                    drawMarker(searchStopDTO)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<SearchStopDTO>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun getBusGPS(id : Int, stoporder : Int, route_name : String) {
        apiService.getBus(id, stoporder).enqueue(object : Callback<AllBusDTO> {
            override fun onResponse(
                call: Call<AllBusDTO>,
                response: Response<AllBusDTO>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    drawBus(response.body()!!, route_name)

                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<AllBusDTO>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun drawMarker(stop : SearchStopDTO){
        // ?????? ????????? ??????
         // ?????? ????????? ?????? ????????? ????????? ???

        // ?????? ??????
        val marker = MapPOIItem()
        marker.apply {
            itemName = stop.name
            mapPoint = MapPoint.mapPointWithGeoCoord(stop.gps_x, stop.gps_y)  // ?????? ????????? ?????? ??????
            markerType = MapPOIItem.MarkerType.BluePin      // ?????? ?????????
        }
        binding.mapView.addPOIItem(marker)

       binding.textStopNameMap.text = stop.name
    }

    fun drawBus(bus : AllBusDTO, route_name : String){
        // ?????? ????????? ??????
        binding.mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(bus.gps_x, bus.gps_y), 0,true)
        // ?????? ??????
        val marker = MapPOIItem()
        marker.apply {
            itemName = route_name
            mapPoint = MapPoint.mapPointWithGeoCoord(bus.gps_x, bus.gps_y)  // ?????? ????????? ?????? ??????
            markerType = MapPOIItem.MarkerType.YellowPin      // ?????? ?????????
        }
        binding.mapView.addPOIItem(marker)
    }

}