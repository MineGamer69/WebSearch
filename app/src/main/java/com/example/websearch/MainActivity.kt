package com.example.websearch


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Setting up our navigation for our toolbar



        val menuBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(menuBar)

        val navhostCtrl = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navhostCtrl.navController

        val build = AppBarConfiguration.Builder(navController.graph)

        val toolbarConfig = build.build()

        menuBar.setupWithNavController(navController, toolbarConfig)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Our inflator command
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuID = item.itemId
        return when (menuID) {
            R.id.action_settings -> {
                //Settings button implementation
                //navController.navigate(R.id.settingsFragment)
                true
            }
            R.id.action_share -> {
                //Our share button implementation
                val navHostFrag = supportFragmentManager.primaryNavigationFragment as NavHostFragment?
                val SearchResultFrag = navHostFrag?.childFragmentManager?.primaryNavigationFragment as? SearchFragment

                val db = SearchHistoryDatabase.getDatabase(this)
                lifecycleScope.launch(Dispatchers.IO) {
                    val latestSearch = db.searchHistoryDao().getAll().lastOrNull()
                    latestSearch?.let {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT,
                                "Hey! check out my Latest search: ${it.searchQuery}, Safe search: ${it.safeSearch}, Type: ${it.searchType}"
                            )
                            type = "text/plain"
                        }
                        startActivity(Intent.createChooser(sendIntent, null))
                    } ?: run {
                            Toast.makeText(this@MainActivity, "No search history found", Toast.LENGTH_SHORT).show()

                    }

                }

                return true
            }


            R.id.helpFragment -> {
                //Help Button implementation
                navController.navigate(R.id.helpFragment2)
                true
            }
            else -> return NavigationUI.onNavDestinationSelected(item, navController)
        }
    }

}