package com.farukaygun.yorozuyalist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.farukaygun.yorozuyalist.App
import com.farukaygun.yorozuyalist.R
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // return if user is not logged in.
        if (!App.isUserLoggedIn) {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}