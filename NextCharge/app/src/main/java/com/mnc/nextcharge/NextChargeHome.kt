package com.mnc.nextcharge

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.mnc.nextcharge.databinding.ActivityNextChargeHomeBinding

class NextChargeHome : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding : ActivityNextChargeHomeBinding
    //private val currentUserViewModel: HomeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNextChargeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val currentUserViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        //val currentUserVM: HomeViewModel by activityViewModels()
        setSupportActionBar(binding.appBarNextChargeHome.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_next_charge_home)

        binding.appBarNextChargeHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
        navView.setupWithNavController(navController)

        /*if(currentUserViewModel.hasNoUserSet()) {
         val toast = Toast.makeText(this,"No user  must Login",Toast.LENGTH_SHORT).show()
        //navController.navigate(R.id.nextChargeLoginFragment2)
        } else {
            val toast = Toast.makeText(this,"Navigate to Home page ",Toast.LENGTH_SHORT).show()
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