package com.carintis.android.quotesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.carintis.android.quotesapp.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

    // Private properties
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(
          topLevelDestinationIds = setOf(
            R.id.discoverListFragment,
            R.id.favoritesFragment,
            R.id.moreFragment
          )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch to AppTheme for displaying the activity
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupActionBar() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationBar() {
        bottom_navigation.setupWithNavController(navController)
    }
}
