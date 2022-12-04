package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.Data.Remote.Client.RetrofitClient
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchRouteDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopDTO
import ac.kr.tukorea.bus_application.Data.Remote.DTO.SearchStopList
import ac.kr.tukorea.bus_application.Data.Remote.Service.ApiService
import ac.kr.tukorea.bus_application.View.Adapter.SearchRouteListAdapter
import ac.kr.tukorea.bus_application.View.Adapter.SearchStopListAdapter
import ac.kr.tukorea.bus_application.databinding.ActivitySearchBinding
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var db : AppDatabase
    val apiService = RetrofitClient.getApiInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        var query = intent.getStringExtra("query")

        db = AppDatabase.getInstance(binding.root.context)

        binding.run {
            searchBus.setOnClickListener {
                searchBarSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,androidx.appcompat.widget.SearchView.OnQueryTextListener
                {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        query = p0!!
                        getRouteList(query!!)
                        return false
                    }
                    override fun onQueryTextChange(p0: String?): Boolean {
                        query = p0!!
                        getRouteList(query!!)
                        return false
                    }
                })
                rvBusSearch.visibility = View.VISIBLE
                rvStopSearch.visibility = View.GONE

                searchBarSearch.setQuery(query, true)

            }
            searchBus.callOnClick()

            searchStop.setOnClickListener {
                searchBarSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,androidx.appcompat.widget.SearchView.OnQueryTextListener
                {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        query = p0!!
                        getStopList(query!!)
                        return false
                    }
                    override fun onQueryTextChange(p0: String?): Boolean {
                        query = p0!!
                        getStopList(query!!)
                        return false
                    }
                })
                rvBusSearch.visibility = View.GONE
                rvStopSearch.visibility = View.VISIBLE

                searchBarSearch.setQuery(query, true)
            }

            rvBusSearch.apply {
                layoutManager= LinearLayoutManager(context)
            }

            rvStopSearch.apply {
                layoutManager = LinearLayoutManager(context)
            }
        }
        setContentView(binding.root)
    }

    fun getStopList(name : String){
        apiService.getStopList(name).enqueue(object : Callback<ArrayList<SearchStopDTO>> {
            override fun onResponse(
                call: Call<ArrayList<SearchStopDTO>>,
                response: Response<ArrayList<SearchStopDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    var id : Int?
                    var checked = arrayListOf<Boolean>()
                    var body = response.body()!!

                    id = db.alarmGettingOffDao().getAlarmGetiingOff().route_id

                    for (i: Int in 0 until body!!.size) {
                        if (id != null && response.body()!![i].id == id)
                            checked!!.add(i, true)
                        else
                            checked!!.add(i, false)
                    }

                    binding.rvStopSearch.adapter = SearchStopListAdapter(body, checked, db)

                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<SearchStopDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }

    fun getRouteList(name : String){
        apiService.getRouteList(name).enqueue(object : Callback<ArrayList<SearchRouteDTO>> {
            override fun onResponse(
                call: Call<ArrayList<SearchRouteDTO>>,
                response: Response<ArrayList<SearchRouteDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    binding.rvBusSearch.adapter = SearchRouteListAdapter(response.body()!!)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<SearchRouteDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }
}