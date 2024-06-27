package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.RequestManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.home.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        navController?.addOnDestinationChangedListener(this)
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            bottomNav.setupWithNavController(navController!!)
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.home ->
                binding.bottomNav.visibility = View.VISIBLE

            R.id.wallets ->
                binding.bottomNav.visibility = View.VISIBLE
            R.id.history ->
                binding.bottomNav.visibility = View.VISIBLE
            R.id.settings ->
                binding.bottomNav.visibility = View.VISIBLE
            else -> binding.bottomNav.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController = null
    }

}