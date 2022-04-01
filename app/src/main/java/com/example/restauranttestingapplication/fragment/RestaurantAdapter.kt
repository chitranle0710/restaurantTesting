package com.example.restauranttestingapplication.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
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
import com.example.restauranttestingapplication.utils.OperatingHoursInformation
import com.example.restauranttestingapplication.utils.RestaurantDiff

class RestaurantAdapter :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var listRes: MutableList<RestaurantData> = arrayListOf()
    private var currentTime: Long? = null

    var onItemClick: (restaurant: RestaurantData) -> Unit = {}
    private val FADE_DURATION = 1000 //FADE_DURATION in milliseconds

    fun updateCurrentTime(dataTime: Long) {
        this.currentTime = dataTime
    }

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RestaurantAdapter.RestaurantViewHolder, position: Int) {
        holder.bind(listRes[position], position, currentTime!!)
        setFadeAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = listRes.size

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        fun bind(restaurant: RestaurantData, position: Int, currentTime: Long) {
            binding.root.setOnClickListener {
                onItemClick.invoke(restaurant)
                Log.d("Adapter", AppUtils.convertTimeToDayOfWeek(currentTime))
                Log.d("Adapter", AppUtils.convertDaysOfWeek(restaurant.operatingHours)[0].dayOfWeek)
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
            getCurrentState(currentTime, restaurant, itemView)
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun getCurrentState(currentTime: Long, restaurant: RestaurantData, itemView: View) {
            val newList = AppUtils.convertDaysOfWeek(restaurant.operatingHours)
            val today = AppUtils.convertTimeToDayOfWeek(currentTime)
            val arrayOpenDaysList: ArrayList<String> = arrayListOf()
            for (i in newList) {
                arrayOpenDaysList.add(i.dayOfWeek)
            }
            if (arrayOpenDaysList.contains(today)) {
                val openHour =
                    AppUtils.parseDate(newList.find { it.dayOfWeek == today }!!.startTime)
                val closeHour = AppUtils.parseDate(newList.find { it.dayOfWeek == today }!!.endTime)
                val currentHour = AppUtils.parseDate(AppUtils.convertTimeToHourMinutes(currentTime))
                if (AppUtils.isBetweenValidTime(openHour, closeHour!!, currentHour!!)) {
                    itemView.findViewById<TextView>(R.id.tvState).text =
                        itemView.resources.getString(R.string.open)
                    itemView.findViewById<ImageView>(R.id.ivState)
                        .setImageResource(R.drawable.bg_circle_open)

                } else {
                    itemView.findViewById<TextView>(R.id.tvState).text =
                        itemView.resources.getString(R.string.close)
                    itemView.findViewById<ImageView>(R.id.ivState)
                        .setImageResource(R.drawable.bg_circle_close)

                }
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

