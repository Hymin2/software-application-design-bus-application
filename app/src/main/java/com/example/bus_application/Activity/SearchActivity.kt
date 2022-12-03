package com.example.bus_application.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.SearchBusListAdapter
import com.example.bus_application.Adapter.SearchStopListAdapter
import com.example.bus_application.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        val query = intent.getStringExtra("query")

        binding.run {
            searchBarSearch.setQuery(query, true)

            searchBus.setOnClickListener {
                rvBusSearch.visibility = View.VISIBLE
                rvStopSearch.visibility = View.GONE
            }
            searchStop.setOnClickListener {
                rvBusSearch.visibility = View.GONE
                rvStopSearch.visibility = View.VISIBLE
            }

            rvBusSearch.apply {
                adapter = SearchBusListAdapter()
                layoutManager= LinearLayoutManager(context)
            }

            rvStopSearch.apply {
                adapter = SearchStopListAdapter()
                layoutManager= LinearLayoutManager(context)
            }
        }
        setContentView(binding.root)
    }
}