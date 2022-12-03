package com.example.bus_application.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bus_application.databinding.ItemRecyclerRouteBinding

class BusRouteAdapter : RecyclerView.Adapter<BusRouteAdapter.BusRoute>(){
    private val list = listOf(1,2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteAdapter.BusRoute {
        val view = ItemRecyclerRouteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BusRoute(view)
    }

    override fun onBindViewHolder(holder: BusRouteAdapter.BusRoute, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BusRoute(private val binding: ItemRecyclerRouteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.stopName.text = "$pos 번째 정류장 이름"
        }
    }
}