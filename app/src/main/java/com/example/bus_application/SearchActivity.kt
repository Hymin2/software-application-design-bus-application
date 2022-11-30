package com.example.bus_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bus_application.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        val query = intent.getStringExtra("query")

        binding.run {
            searchBarSearch.setQuery(query, true)
        }
        setContentView(binding.root)
    }
}