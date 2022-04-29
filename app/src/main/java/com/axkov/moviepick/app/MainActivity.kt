package com.axkov.moviepick.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration

class MainActivity : AppCompatActivity() {
    private var _navController: NavController? = null
    private val navController get() = checkNotNull(_navController) { "NavController isn't initialized" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHost

        _navController = navHostFragment.navController

//        val appBarConfiguration = AppBarConfiguration(navController.graph)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupBottomNavMenu(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        bottomNav?.setupWithNavController(navController)
    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        //        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}