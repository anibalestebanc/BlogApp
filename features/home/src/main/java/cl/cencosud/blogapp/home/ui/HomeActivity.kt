package cl.cencosud.blogapp.home.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.setupWithNavController
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.home.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as DynamicNavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNavigationView.setupWithNavController(navController)
        hideBottomNavWhenNeeded()
    }

    private fun hideBottomNavWhenNeeded() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.cameraFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.profileFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}