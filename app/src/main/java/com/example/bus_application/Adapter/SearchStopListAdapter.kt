package com.example.bus_application.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bus_application.databinding.ItemRecyclerSearchBusBinding
import com.example.bus_application.databinding.ItemRecyclerSearchStopBinding

class SearchStopListAdapter : RecyclerView.Adapter<SearchStopListAdapter.MyStopList>() {
    private val list = listOf(1,2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStopList {
        val view = ItemRecyclerSearchStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyStopList(view)
    }

    override fun onBindViewHolder(holder: MyStopList, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyStopList(private val binding: ItemRecyclerSearchStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.stopName.text = "정류장 이름"
            binding.stopRegion.text = "정류장 지역"

            binding.stopBookmarkCb.setOnCheckedChangeListener { compoundButton, b ->

            }
        }
    }
}