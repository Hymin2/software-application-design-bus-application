package com.example.bus_application.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bus_application.databinding.ItemRecyclerBookmarkStopBinding

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.MyStopList>(){
    private val list = listOf(1,2,3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStopList {
        val view = ItemRecyclerBookmarkStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyStopList(view)
    }

    inner class MyStopList(private val binding: ItemRecyclerBookmarkStopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            binding.textStopName.text = "버스정류장 $pos 번"
            binding.textStopDirection.text = "정류장 방면 $pos 번"
            binding.rvBus.apply {
                adapter = BusAdapter()
                layoutManager = LinearLayoutManager(binding.rvBus.context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.MyStopList, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}