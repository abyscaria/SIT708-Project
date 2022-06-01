package com.mnc.nextcharge

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mnc.nextcharge.databinding.ActivityNextChargeHomeBinding

class NextChargeHome : AppCompatActivity() {
    private val TAG = "HomeFragment"
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding : ActivityNextChargeHomeBinding
    //private val currentUserViewModel: HomeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextChargeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUserViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment_content_next_charge_home)

        setSupportActionBar(binding.appBarNextChargeHome.toolbar)

       /* binding.appBarNextChargeHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout : DrawerLayout = binding.drawerLayout
        val navView : NavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)    //}

       /*- if(currentUserViewModel.hasNoUserSet()) {
            Log.d(TAG, "NextChargeHome before calling Login : NCH Activity before calling Login fragment ")
            navController.navigate(R.id.nextChargeLoginFragment)
        }*/
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.next_charge_home, menu)
        return true
    }

    override fun onSupportNavigateUp() : Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_next_charge_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}