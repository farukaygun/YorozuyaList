package com.farukaygun.yorozuyalist.view.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseBottomSheetDialog<VBinding : ViewBinding> : BottomSheetDialogFragment() {
	protected lateinit var binding: VBinding

	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		throwable.localizedMessage?.let { Log.e("Fragment Exception", it) }
	}


	abstract fun getViewBinding(): VBinding
	abstract fun start()

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
		start()
	}

	fun lifecycleLaunch(launch: suspend () -> Unit) {
		viewLifecycleOwner.lifecycleScope.launch(exceptionHandler) {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				launch.invoke()
			}
		}
	}
}