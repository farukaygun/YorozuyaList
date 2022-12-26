package com.farukaygun.yorozuyalist.view.settings

import com.farukaygun.yorozuyalist.databinding.FragmentSettingsBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
	override fun getViewBinding(): FragmentSettingsBinding =
		FragmentSettingsBinding.inflate(layoutInflater)

	override val isAppbarVisible: Boolean = false


	override fun start() {
	}
}