package com.farukaygun.yorozuyalist.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefsHelper = SharedPrefsHelper(applicationContext)

        if (!sharedPrefsHelper.getBool("isLoggedIn") || sharedPrefsHelper.getString("accessToken") == "null") {
            Intent(this@MainActivity, LoginActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }
}