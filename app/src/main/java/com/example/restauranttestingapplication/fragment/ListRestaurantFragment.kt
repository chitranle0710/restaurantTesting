package com.example.restauranttestingapplication.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.restauranttestingapplication.base.BaseFragment
import com.example.restauranttestingapplication.databinding.FragmentListRestaurantBinding

class ListRestaurantFragment : BaseFragment() {
    private var binding: FragmentListRestaurantBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRestaurantBinding.inflate(layoutInflater)
        return binding!!.root
    }

}