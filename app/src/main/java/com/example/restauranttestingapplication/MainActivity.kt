package com.example.restauranttestingapplication

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navInflater.inflate(R.navigation.main_navigation)
    }


}