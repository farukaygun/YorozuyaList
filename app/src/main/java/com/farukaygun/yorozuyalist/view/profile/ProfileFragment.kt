package com.farukaygun.yorozuyalist.view.profile

import com.farukaygun.yorozuyalist.databinding.FragmentProfileBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val isAppbarVisible: Boolean = true
    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)


    override fun start() {
    }
}