package com.farukaygun.yorozuyalist.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ActivityMainBinding
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import com.farukaygun.yorozuyalist.view.login.LoginActivity

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var navController: NavController
	private lateinit var sharedPrefsHelper: SharedPrefsHelper


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		// DynamicColors.applyToActivitiesIfAvailable(application)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		sharedPrefsHelper = SharedPrefsHelper(applicationContext)
		if (!sharedPrefsHelper.getBool("isLoggedIn") || sharedPrefsHelper.getString("accessToken") == "null") {
			Intent(this@MainActivity, LoginActivity::class.java).apply {
				startActivity(this)
				finish()
			}
		}

		// bottom nav
		navController = findNavController(R.id.fragmentContainerView)
		binding.bottomNavigationBar.setupWithNavController(navController)

		// toolbar
		setSupportActionBar(binding.topAppBar)
		binding.topAppBar.setNavigationOnClickListener { navController.navigate(R.id.action_global_baseSearchFragment) }
	}

	// toolbar right menu bind
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.app_bar_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	// toolbar right menu click
//	override fun onOptionsItemSelected(item: MenuItem): Boolean {
//		when (item.itemId) {
//			R.id.settings -> navController.navigate(R.id.settingsFragment)
//			R.id.about -> navController.navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
//		}
//		return super.onOptionsItemSelected(item)
//	}

	fun isNavViewVisible(visibility: Int) {
		binding.bottomNavigationBar.visibility = visibility
	}
}