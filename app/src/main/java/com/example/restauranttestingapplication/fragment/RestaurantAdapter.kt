package com.example.restauranttestingapplication.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranttestingapplication.R
import com.example.restauranttestingapplication.databinding.ItemRestaurantBinding
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.utils.RestaurantDiff

class RestaurantAdapter :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var listRes: MutableList<Restaurants> = mutableListOf()
    val onItemClick: (restaurant: Restaurants) -> Unit = {}
    private val FADE_DURATION = 1000 //FADE_DURATION in milliseconds

    fun updateData(data: MutableList<Restaurants>) {
        val diffCallback = RestaurantDiff(listRes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        if (this.listRes == data) {
            return
        }
        listRes.clear()
        listRes.addAll(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.RestaurantViewHolder {
        return RestaurantViewHolder(
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.RestaurantViewHolder, position: Int) {
        holder.bind(listRes[position], position)
        setFadeAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = listRes.size

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(restaurant: Restaurants, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.invoke(restaurant)
            }
            binding.tvNameStore.text = "${restaurant.name} Restaurant"
            binding.tvState.text = "Operation: ${
                if (restaurant.operation) itemView.context.resources.getString(R.string.open) else itemView.context.resources.getString(
                    R.string.close
                )
            }"
        }
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }
}

