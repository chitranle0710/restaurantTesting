package com.example.restauranttestingapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.restauranttestingapplication.model.Restaurants

class RestaurantDiff(
    private val oldList: MutableList<Restaurants>,
    private val newList: MutableList<Restaurants>
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
        oldList[oldItemPosition].id === newList[newItemPosition].id

    /**
     *  Called by the DiffUtil when it wants to check whether two items have the same data.
     *  DiffUtil uses this information to detect if the contents of an item has changed.
     *
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }
}