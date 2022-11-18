package com.farukaygun.yorozuyalist.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.farukaygun.yorozuyalist.databinding.ActivityLoginBinding
import com.farukaygun.yorozuyalist.util.Constants
import com.farukaygun.yorozuyalist.util.Constants.YOROZUYA_PAGELINK
import com.farukaygun.yorozuyalist.util.Extensions.openInCustomTabs
import com.farukaygun.yorozuyalist.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModelLogin : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {
            openInCustomTabs(viewModelLogin.loginUrl)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModelLogin.parseIntentData(intent)
    }
}
