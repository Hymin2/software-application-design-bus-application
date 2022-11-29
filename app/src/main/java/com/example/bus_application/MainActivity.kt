package com.example.bus_application

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.BookmarkAdapter
import com.example.bus_application.Adapter.BusAdapter
import com.example.bus_application.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var BookmarkAdapter : BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        BookmarkAdapter = BookmarkAdapter()

        binding.run {
            radioBtnBookmark.setOnClickListener {
                rvBookmark.visibility = View.VISIBLE
                textRide.visibility = View.GONE
                textGetoff.visibility = View.GONE
            }
            radioBtnRide.setOnClickListener {

                rvBookmark.visibility = View.GONE
                textRide.visibility = View.VISIBLE
                textGetoff.visibility = View.GONE
            }
            radioBtnGetoff.setOnClickListener {
                rvBookmark.visibility = View.GONE
                textRide.visibility = View.GONE
                textGetoff.visibility = View.VISIBLE
            }

            searchBarMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
                    intent.putExtra("query", query)
                    startActivity(intent)

                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }

            })

            rvBookmark.apply{
                adapter= BookmarkAdapter()
                layoutManager=LinearLayoutManager(context)
            }
        }


    }
}



