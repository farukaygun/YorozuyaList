package com.farukaygun.yorozuyalist.view.about

import android.os.Bundle
import com.farukaygun.yorozuyalist.databinding.FragmentAboutBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlin.coroutines.CoroutineContext

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override fun getViewBinding(): FragmentAboutBinding = FragmentAboutBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = false

    override fun start(savedInstanceState: Bundle?) {
    }
}