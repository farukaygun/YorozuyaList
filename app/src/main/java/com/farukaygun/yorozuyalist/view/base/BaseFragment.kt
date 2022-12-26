package com.farukaygun.yorozuyalist.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.farukaygun.yorozuyalist.view.main.MainActivity
import kotlinx.coroutines.*

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {
	protected lateinit var binding: VBinding
	private lateinit var activity: MainActivity


	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		throwable.localizedMessage?.let { Log.e("Fragment Exception", it) }
	}


	abstract val isAppbarVisible: Boolean
	abstract fun getViewBinding(): VBinding
	abstract fun start()

	fun lifecycleLaunch(launch: suspend () -> Unit) {
		viewLifecycleOwner.lifecycleScope.launch(exceptionHandler) {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				launch.invoke()
			}
		}
	}

	override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
}