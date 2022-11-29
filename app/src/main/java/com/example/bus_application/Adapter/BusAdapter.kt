package com.example.bus_application.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bus_application.R
import com.example.bus_application.databinding.ItemRecyclerBookmarkBusBinding
import com.example.bus_application.databinding.ItemRecyclerBookmarkStopBinding

class BusAdapter : RecyclerView.Adapter<BusAdapter.MyBusList>(){
    private val list = listOf(1,2)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusAdapter.MyBusList {
        val view = ItemRecyclerBookmarkBusBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyBusList(view)
    }

    override fun onBindViewHolder(holder: BusAdapter.MyBusList, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyBusList(private val binding: ItemRecyclerBookmarkBusBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.textBusNum.text = "버스 $pos 번"
            binding.textRemainTime.text = "남은 시간 $pos 분"
        }
    }

}