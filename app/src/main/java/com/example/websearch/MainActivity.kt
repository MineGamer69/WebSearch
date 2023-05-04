//Made by Aaryan Kapoor and Matt Nova
package com.example.websearch

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private var nightModeToggle: AppCompatImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(menuBar)

        val navhostCtrl = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navhostCtrl.navController

        val build = AppBarConfiguration.Builder(navController.graph)

        val toolbarConfig = build.build()

        menuBar.setupWithNavController(navController, toolbarConfig)

        menuBar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)

        nightModeToggle = menu?.findItem(R.id.night_mode_toggle)?.actionView as? AppCompatImageButton
        nightModeToggle?.setImageResource(getNightModeToggleIcon(AppCompatDelegate.getDefaultNightMode()))
        nightModeToggle?.setOnClickListener {
            toggleNightMode()
        }
        menu?.findItem(R.id.night_mode_toggle)?.setActionView(nightModeToggle)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                // ...
                true
            }
            R.id.helpFragment -> {
                navController.navigate(R.id.helpFragment2)
                true
            }
            R.id.night_mode_toggle -> {
                toggleNightMode()
                true
            }
            else -> NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)
        }
    }

    private fun toggleNightMode() {
        val nightMode = if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
        nightModeToggle?.setImageResource(getNightModeToggleIcon(AppCompatDelegate.getDefaultNightMode()))
    }

    private fun getNightModeToggleIcon(nightMode: Int): Int {
        return if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            R.drawable.sun
        } else {
            R.drawable.moon
        }
    }
}
