package com.farukaygun.yorozuyalist.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.farukaygun.yorozuyalist.databinding.ActivityLoginBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import com.farukaygun.yorozuyalist.view.main.MainActivity
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
            viewModelLogin.openInCustomTabs(this@LoginActivity, viewModelLogin.loginUrl)
        }

        job = lifecycleScope.launch(exceptionHandler) {
            viewModelLogin.accessToken.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { data ->
                            sharedPrefsHelper = SharedPrefsHelper()
                            sharedPrefsHelper.saveString("accessToken", data.accessToken)
                            sharedPrefsHelper.saveString("refreshToken", data.refreshToken)
                            sharedPrefsHelper.saveBool("isLoggedIn", true)

                            Intent(this@LoginActivity, MainActivity::class.java).apply {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(this)
                                finish()
                            }
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(this@LoginActivity, "${it.message}", Toast.LENGTH_LONG).show()
                    else -> {}
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
