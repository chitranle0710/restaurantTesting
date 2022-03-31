package com.example.restauranttestingapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.restauranttestingapplication.model.RestaurantData
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.model.RestaurantsTime

class RestaurantDiff(
    private val oldList: MutableList<RestaurantData>,
    private val newList: MutableList<RestaurantData>
) : DiffUtil.Callback() {

    //Returns the size of the old list.
    override fun getOldListSize() = oldList.size

    //Returns the size of the new list
    override fun getNewListSize() = newList.size

    /**
     *  Called by the DiffUtil to decide whether two object represent the same Item.
     *  For example, if your items have unique ids, this method should check their id equality.
     *
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].name === newList[newItemPosition].name

    /**
     *  Called by the DiffUtil when it wants to check whether two items have the same data.
     *  DiffUtil uses this information to detect if the contents of an item has changed.
     *
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}