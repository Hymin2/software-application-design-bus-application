package com.example.bus_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bus_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var profileAdapter: ProfileAdapter
    val datas = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        initRecycler()
    }
    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)
        binding.rvProfile.adapter = profileAdapter


        datas.apply {
            add(ProfileData(name = "mary", age = 24))
            add(ProfileData(name = "jenny", age = 26))
            add(ProfileData(name = "jhon", age = 27))
            add(ProfileData(name = "ruby", age = 21))
            add(ProfileData(name = "yuna", age = 23))
            add(ProfileData(name = "yu21312na", age = 23))

            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()

        }
    }
}



