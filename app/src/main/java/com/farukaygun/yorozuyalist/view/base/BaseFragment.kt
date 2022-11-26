package com.farukaygun.yorozuyalist.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.farukaygun.yorozuyalist.view.main.MainActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VBinding: ViewBinding> : Fragment(), CoroutineScope {
    protected lateinit var binding: VBinding
    private lateinit var activity: MainActivity
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Fragment Exception", ": ${throwable.localizedMessage}")
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + exceptionHandler


    abstract val isAppbarVisible: Boolean
    abstract fun getViewBinding(): VBinding
    abstract fun start()


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
        activity = getActivity() as MainActivity
        start()
    }

    override fun onResume() {
        super.onResume()

        if (!isAppbarVisible) {
            activity.supportActionBar?.hide()
            activity.isNavViewVisible(View.GONE)
        }
    }

    override fun onStop() {
        super.onStop()

        if (!isAppbarVisible) {
            activity.supportActionBar?.show()
            activity.isNavViewVisible(View.VISIBLE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}