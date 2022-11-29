package com.example.bus_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bus_application.Adapter.BookmarkAdapter
import com.example.bus_application.Adapter.BusAdapter
import com.example.bus_application.Fragment.BookmarkFragment
import com.example.bus_application.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var BookmarkAdapter : BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookmarkfragment = BookmarkFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_bookmark, BookmarkFragment()).commit()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        BookmarkAdapter = BookmarkAdapter()


/*
        binding.rvBookmark.apply{
            adapter= BookmarkAdapter()
            layoutManager=LinearLayoutManager(context)
        }
        */
    }

    override fun passDataCom(editTextInput : String) {
        val bundle = Bundle()
        bundle.putString("message", editTextInput)

        val transaction = this.supportFragmentManager.beginTransaction()
        val
    }

}



