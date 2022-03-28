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
import com.example.restauranttestingapplication.model.Days
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.utils.beGone
import com.example.restauranttestingapplication.utils.beVisible
import com.example.restauranttestingapplication.utils.toArrayList
import com.example.restauranttestingapplication.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ListRestaurantFragment : BaseFragment() {
    private var binding: FragmentListRestaurantBinding? = null
    private val viewModel: RestaurantViewModel by viewModels()
    private var isOpen: Boolean = true

    private val adapter by lazy {
        RestaurantAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRestaurantBinding.inflate(layoutInflater)

        viewModel.getData()

        registerObserver()
        initRecyclerView()

        onRadioButtonClicked()
        onClick()

        return binding!!.root
    }

    private fun onClick() {
        binding?.btnClearData?.setOnClickListener {
            viewModel.clearData()
        }
        binding?.btnAddData?.setOnClickListener {
            val nameStore = binding?.etStore?.text.toString()
            if (nameStore.isEmpty()) {
                binding?.tvError.beVisible()
                return@setOnClickListener
            }
            binding?.etStore?.setText("")
            binding?.tvError.beGone()
            insertData(nameStore)
        }
    }

    private fun onRadioButtonClicked() {
        binding?.radioOpen?.isChecked = true
        isOpen = binding?.radioOpen?.isChecked == true
    }

    private fun registerObserver() {
        viewModel.loadingData.observe(requireActivity()) {
            progressBar(it)
        }
        viewModel.restaurantMutableLiveData.observe(requireActivity()) { listRes ->
            if (listRes.isNullOrEmpty()) {
                adapter.updateData(listRes.toArrayList())
                binding?.layoutNoData?.root?.beVisible()
            } else {
                binding?.layoutNoData?.root?.beGone()
                adapter.updateData(listRes.toArrayList())
            }
        }
    }

    private fun initRecyclerView() {
        binding?.rvRes?.setHasFixedSize(true)
        binding?.rvRes?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.rvRes?.adapter = adapter
    }

    private fun insertData(nameStore: String) {
        viewModel.insertData(
            Restaurants(
                null,
                name = nameStore, true, arrayListOf(
                    Days(
                        1, "Monday", 1646049796000, 1646078596000
                    ),
                    Days(
                        2, "Tuesday", 1646049796000, 1646078596000
                    ), Days(
                        3, "Wednesday", 1646049796000, 1646078596000
                    ), Days(
                        4, "Thursday", 1646049796000, 1646078596000
                    ), Days(
                        5, "Friday", 1646049796000, 1646078596000
                    ),
                    Days(
                        6, "Saturday", 1646049796000, 1646078596000
                    ),
                    Days(
                        7, "Sunday", 1646049796000, 1646078596000
                    )
                )
            )
        )
    }

}