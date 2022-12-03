package com.example.bus_application.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bus_application.Activity.RouteActivity
import com.example.bus_application.Activity.SearchActivity
import com.example.bus_application.databinding.ItemRecyclerSearchBusBinding

class SearchBusListAdapter : Adapter<SearchBusListAdapter.MyBusList>(){
    private val list = listOf(1,2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBusListAdapter.MyBusList {
        val view = ItemRecyclerSearchBusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyBusList(view)
    }

    override fun onBindViewHolder(holder: SearchBusListAdapter.MyBusList, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyBusList(private val binding: ItemRecyclerSearchBusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.busName.text = "버스 이름"
            binding.busRegion.text = "버스 지역 | 기점 - 종점"
            binding.busTime.text = "첫차 시간 ~ 막차 시간"

            binding.root.setOnClickListener {
                var intent = Intent(binding.root.context, RouteActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }

}