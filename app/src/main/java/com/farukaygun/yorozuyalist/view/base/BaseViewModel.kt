package com.farukaygun.yorozuyalist.view.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		throwable.localizedMessage?.let { Log.e("ViewModel Exception", it) }
	}


	fun viewModelLaunch(launch: suspend () -> Unit) {
		viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
			launch.invoke()
		}
	}
}