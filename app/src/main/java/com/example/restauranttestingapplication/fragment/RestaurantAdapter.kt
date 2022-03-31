package com.example.restauranttestingapplication.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranttestingapplication.R
import com.example.restauranttestingapplication.databinding.ItemRestaurantBinding
import com.example.restauranttestingapplication.model.Days
import com.example.restauranttestingapplication.model.RestaurantData
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.model.RestaurantsTime
import com.example.restauranttestingapplication.utils.AppUtils
import com.example.restauranttestingapplication.utils.RestaurantDiff

class RestaurantAdapter :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var listRes: MutableList<RestaurantData> = arrayListOf()

    var onItemClick: (restaurant: RestaurantData) -> Unit = {}
    private val FADE_DURATION = 1000 //FADE_DURATION in milliseconds

    fun updateData(data: ArrayList<RestaurantData>?) {
        data ?: return
        if (this.listRes == data) {
            return
        }
        val diffCallback = RestaurantDiff(listRes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
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
        fun bind(restaurant: RestaurantData, position: Int) {
            binding.root.setOnClickListener {
                onItemClick.invoke(restaurant)
            }
            binding.tvNameStore.text = restaurant.name
            binding.tvState.text = ""
            binding.tvSchedule.text = restaurant.operatingHours

            binding.ivDown.setOnClickListener {
                animationOnChoosing(
                    binding.ivDown.findViewById(R.id.ivDown)!!,
                    !binding.ivDown.findViewById<ImageView>(R.id.ivDown)!!.isSelected,
                    binding.tvSchedule.findViewById(R.id.tvSchedule)!!,
                    binding.tvSchedule.findViewById(R.id.tvSchedule)!!
                )
            }
        }
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }

    private fun animationOnChoosing(
        viewSelected: View,
        expand: Boolean,
        viewVisible: View,
        viewLine: View
    ) {
        val animator = AppUtils.getValueAnimator(expand, 100, AccelerateDecelerateInterpolator())
        { progress ->
            setExpandProgress(viewSelected, progress)
        }
        animator.doOnStart {
            viewSelected.isSelected = expand
            viewVisible.isVisible = expand
            viewLine.isVisible = !expand
        }
        animator.start()
    }

    private fun setExpandProgress(view: View, progress: Float) {
        view.rotation = 180 * progress
    }
}

