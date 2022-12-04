package ac.kr.tukorea.bus_application.Adapter

import ac.kr.tukorea.bus_application.DTO.RouteDetailsStopDTO
import ac.kr.tukorea.bus_application.databinding.ItemRecyclerRouteBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RouteDetailsListAdapter(private val items : ArrayList<RouteDetailsStopDTO>) : RecyclerView.Adapter<RouteDetailsListAdapter.MyRouteDetails>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRouteDetails {
        val view = ItemRecyclerRouteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyRouteDetails(view)
    }

    override fun onBindViewHolder(holder: MyRouteDetails, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyRouteDetails(private val binding: ItemRecyclerRouteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos : Int){
            val item = items.get(pos)

            when(item.stop_order){
                1 -> binding.upView.visibility = View.INVISIBLE
                items.size -> binding.downView.visibility = View.INVISIBLE
                else -> {
                    binding.upView.visibility = View.VISIBLE
                    binding.downView.visibility = View.VISIBLE
                }
            }

            if(item.stop_order != 1 && !item.updown.equals(items.get(item.stop_order - 2).updown)){
                binding.routeTurn.visibility = View.VISIBLE
                binding.arrowImage.visibility = View.INVISIBLE
            }
            else{
                binding.routeTurn.visibility = View.INVISIBLE
                binding.arrowImage.visibility = View.VISIBLE
            }

            binding.stopName.text = item.stop_name
            binding.mobileNo.text = item.mobile_no

            binding.root.setOnClickListener {

            }
        }
    }
}