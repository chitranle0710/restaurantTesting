package com.example.restauranttestingapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restauranttestingapplication.base.BaseFragment
import com.example.restauranttestingapplication.databinding.FragmentListRestaurantBinding
import com.example.restauranttestingapplication.utils.AppUtils
import com.example.restauranttestingapplication.utils.toArrayList
import com.example.restauranttestingapplication.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListRestaurantFragment : BaseFragment() {
    private var binding: FragmentListRestaurantBinding? = null
    private val viewModel: RestaurantViewModel by viewModels()

    private val adapter by lazy {
        RestaurantAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRestaurantBinding.inflate(layoutInflater)

        registerObserver()
        initRecyclerView()

        return binding!!.root
    }

    private fun registerObserver() {
        viewModel.getDataRemote()
        viewModel.restaurantTimeMutableLiveData.observe(this) { res ->
            Log.d("Fragment", AppUtils.convertTime(res.timestamp))
            adapter.updateData(res.restaurants.toArrayList())
        }
        viewModel.loadingData.observe(requireActivity()) {
            progressBar(it)
        }

    }

    private fun initRecyclerView() {
        binding?.rvRes?.setHasFixedSize(true)
        binding?.rvRes?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.rvRes?.adapter = adapter
    }

}