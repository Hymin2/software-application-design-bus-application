package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.Remote.Client.RetrofitClient
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.databinding.ActivityKakaoMapBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.Service.ApiService
import ac.kr.tukorea.bus_application.View.Adapter.SearchRouteListAdapter
import ac.kr.tukorea.bus_application.View.Adapter.SearchStopListAdapter
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMapBinding
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var intent = getIntent().getIntExtra("stop_id", 0)
        getStopInfo(intent)

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

                    drawMarker(searchStopDTO)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<SearchStopDTO>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun drawMarker(stop : SearchStopDTO){
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
}