package com.farukaygun.yorozuyalist.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VBinding: ViewBinding> : Fragment(), CoroutineScope {
    protected lateinit var binding: VBinding
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Fragment Exception", ": ${throwable.localizedMessage}")
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + exceptionHandler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    abstract fun getViewBinding(): VBinding
    abstract fun start()

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}