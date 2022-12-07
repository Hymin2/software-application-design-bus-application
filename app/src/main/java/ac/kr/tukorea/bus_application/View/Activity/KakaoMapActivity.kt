package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.Remote.Client.RetrofitClient
import ac.kr.tukorea.bus_application.Data.Remote.DTO.AllBusDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.databinding.ActivityKakaoMapBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.Service.ApiService
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMapBinding
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)
    var all_bus = arrayListOf<AllBusDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var intent = getIntent().getIntExtra("stop_id", 0)
        var intent2 = getIntent().getIntExtra("route_id",0)
        getStopInfo(intent)
        getBusInfo(intent2)

        Log.d("stopoid",intent.toString())
    }

    fun getStopInfo(id : Int) {
        apiService.getStopById(id).enqueue(object : Callback<SearchStopDTO> {
            override fun onResponse(
                call: Call<SearchStopDTO>,
                response: Response<SearchStopDTO>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    var searchStopDTO = response.body()!!

                    drawStopMarker(searchStopDTO)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<SearchStopDTO>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun drawStopMarker(stop : SearchStopDTO){
        // 지도 중심점 설정
        binding.mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(stop.gps_x, stop.gps_y), 0,true) // 해당 좌표가 화면 중심에 오도록 함

        // 마커 생성
        val marker = MapPOIItem()
        marker.apply {
            itemName = stop.name
            mapPoint = MapPoint.mapPointWithGeoCoord(stop.gps_x, stop.gps_y)  // 해당 좌표에 마커 표시
            markerType = MapPOIItem.MarkerType.BluePin      // 기본 블루핀
        }
        binding.mapView.addPOIItem(marker)

       binding.textStopNameMap.text = stop.name
    }

    fun getBusInfo(routeid : Int) {
        apiService.getAllBus(routeid).enqueue(object : Callback<ArrayList<AllBusDTO>> {
            override fun onResponse(
                call: Call<ArrayList<AllBusDTO>>,
                response: Response<ArrayList<AllBusDTO>>
            ) {
                if(response.isSuccessful && response.code() == 200){
                    all_bus = response.body()!!

                    drawBusMarker(all_bus)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<AllBusDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }


        })
    }

    fun drawBusMarker(bus: ArrayList<AllBusDTO>){
        // 마커 생성
        val marker = MapPOIItem()
        marker.apply {
            for (i : Int in 1..bus.size) {
                itemName = bus[i].bus_id
                mapPoint = MapPoint.mapPointWithGeoCoord(bus[i].gps_x, bus[i].gps_y)  // 해당 좌표에 마커 표시
                markerType = MapPOIItem.MarkerType.RedPin
            }
        }
        binding.textBusNumMap.text = bus[0].bus_id
        binding.mapView.addPOIItem(marker)
    }
}