package com.farukaygun.yorozuyalist.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.farukaygun.yorozuyalist.databinding.ActivityLoginBinding
import com.farukaygun.yorozuyalist.util.Extensions.openInCustomTabs
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import com.farukaygun.yorozuyalist.viewmodel.LoginViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModelLogin : LoginViewModel by viewModels()
    private var job: Job? = null
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("LoginActivity exceptionHandler", ": ${throwable.localizedMessage}")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            openInCustomTabs(viewModelLogin.loginUrl)
        }

        job = lifecycleScope.launch(Dispatchers.Default + exceptionHandler) {
            viewModelLogin.accessToken.collectLatest {
                it?.let {
                    sharedPrefsHelper = SharedPrefsHelper(applicationContext)
                    sharedPrefsHelper.saveString("accessToken", it.accessToken)
                    sharedPrefsHelper.saveString("refreshToken", it.refreshToken)
                    sharedPrefsHelper.saveBool("isLoggedIn", true)

                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(this)
                        finish()
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModelLogin.parseIntentData(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
