package com.farukaygun.yorozuyalist.view.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ViewModel Exception", ": ${throwable.localizedMessage}")
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + exceptionHandler


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}