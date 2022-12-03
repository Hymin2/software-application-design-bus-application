package com.example.bus_application.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.BusRouteAdapter
import com.example.bus_application.databinding.ActivityRouteBinding

class RouteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRouteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            rvRouteList.apply{
                adapter = BusRouteAdapter()
                layoutManager = LinearLayoutManager(context)
            }
        }

    }
}