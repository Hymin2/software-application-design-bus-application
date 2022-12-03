package com.example.bus_application.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.SearchBusListAdapter
import com.example.bus_application.Adapter.SearchStopListAdapter
import com.example.bus_application.DTO.SearchStopDTO
import com.example.bus_application.RetrofitClient
import com.example.bus_application.Service.ApiService
import com.example.bus_application.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var items : MutableList<SearchStopDTO>

    val apiService = RetrofitClient.getStopInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        val query = intent.getStringExtra("query")

        binding.run {
            searchBarSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,androidx.appcompat.widget.SearchView.OnQueryTextListener
            {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    getApiService(p0!!)
                    return false
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    getApiService(p0!!)
                    return false
                }
            })

            searchBus.setOnClickListener {
                searchBarSearch.setQuery(query, true)

                rvBusSearch.visibility = View.VISIBLE
                rvStopSearch.visibility = View.GONE
            }

            searchStop.setOnClickListener {
                searchBarSearch.setQuery(query, true)

                rvBusSearch.visibility = View.GONE
                rvStopSearch.visibility = View.VISIBLE
            }

            rvBusSearch.apply {
                adapter = SearchBusListAdapter()
                layoutManager= LinearLayoutManager(context)
            }

            rvStopSearch.apply {
                layoutManager = LinearLayoutManager(context)
            }

        }
        setContentView(binding.root)
    }

    fun getApiService(name : String){
        apiService.getStopList(name).enqueue(object : Callback<MutableList<SearchStopDTO>> {
            override fun onResponse(
                call: Call<MutableList<SearchStopDTO>>,
                response: Response<MutableList<SearchStopDTO>>,
            ) {
                if(response.isSuccessful && response.code() == 200){
                    items = response.body()!!

                    binding.rvStopSearch.adapter = SearchStopListAdapter(items)
                    Log.d("retrofit2", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<MutableList<SearchStopDTO>>, t: Throwable) {
                Log.d("retrofit2","failed" + t)
            }
        })
    }
}