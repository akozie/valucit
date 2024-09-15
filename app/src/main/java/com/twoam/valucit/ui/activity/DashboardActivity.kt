package com.twoam.valucit.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.twoam.valucit.R
import com.twoam.valucit.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
//    private lateinit var drawerCloseIcon: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDashboard.dashboardActivityToolbar)

        navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navView
        val navigationViewHeader = navigationView.getHeaderView(0)
      //  drawerCloseIcon = navigationViewHeader.findViewById(R.id.nav_drawer_close_icon_image_view)
        bottomNavigationView =
            binding.appBarDashboard.contentDashboard.dashboardActivityBottomNavigationView

        appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
        bottomNavigationView.setupWithNavController(navController)

        /* Close Drawer Icon */
//        drawerCloseIcon.setOnClickListener {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }

        /* Setup navigation change listener */
        onDestinationChangedListener()


        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.settings -> {
//                    findNavController(R.id.nav_host_fragment_content_dashboard).navigate(R.id.settings)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener true
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun onDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
          //  toolbarFragmentName.text = destination.label ?: getString(androidx.navigation.ui.R.string.app_name)
           // toolbarFragmentName.setTextColor(Color.WHITE)
            when (destination.id) {
                R.id.homeFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.cashFlowFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.assetsFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.accountFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.receiptDetailsFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

}