package com.rajabd.mymoviesappmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.NavigationUI
import com.rajabd.mymoviesappmade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val activityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        NavigationUI.setupWithNavController(activityMainBinding.bottomNav, navController)
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as DynamicNavHostFragment).navController
    }

}
