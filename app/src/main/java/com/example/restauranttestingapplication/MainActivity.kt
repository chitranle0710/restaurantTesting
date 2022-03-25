package com.example.restauranttestingapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.restauranttestingapplication.base.BaseActivity
import com.example.restauranttestingapplication.databinding.ActivityMainBinding
import com.example.restauranttestingapplication.model.Days
import com.example.restauranttestingapplication.model.Restaurants
import com.example.restauranttestingapplication.utils.SharePrefs
import com.example.restauranttestingapplication.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
        if (SharePrefs.getPutData(this) == false) {
            SharePrefs.savePutData(this, true)
            hardCodeInsert()
        }
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navInflater.inflate(R.navigation.main_navigation)
    }

    private fun registerObserver() {
        viewModel.restaurantMutableLiveData.observe(this) {
            Log.d("MainActivity", "$it")
        }
    }

    private fun hardCodeInsert() {
        viewModel.insertData(
            Restaurants(
                1, "desc 1", true, arrayListOf(
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