package com.example.websearch


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider
import androidx.core.app.ShareCompat
import androidx.core.view.MenuItemCompat
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
                //val navHostFrag = supportFragmentManager.primaryNavigationFragment as NavHostFragment?
                //val SearchResultFrag = navHostFrag?.childFragmentManager?.primaryNavigationFragment as? SearchResultFragment

                //val bundle = SearchResultFrag?.arguments
                //val mN = bundle?.let { SearchResultFragmentArgs.fromBundle(it).movName.toString() }
                //val cou = bundle?.let { SearchResultFragmentArgs.fromBundle(it).country.toString() }


//                val sendIntent: Intent = Intent().apply {
//                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_TEXT,
//                        "Look what movie I am searching for! Movie Name: $mN Country: $cou"
//                    )
//                    type = "text/plain"
//                }
//                val shareIntent = Intent.createChooser(sendIntent, null)
//                startActivity(shareIntent)
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