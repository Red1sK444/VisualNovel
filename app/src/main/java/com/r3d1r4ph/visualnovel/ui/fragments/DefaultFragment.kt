package com.r3d1r4ph.visualnovel.ui.fragments

import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentDefaultBinding
import com.r3d1r4ph.visualnovel.domain.Screen

class DefaultFragment : BaseFragment(R.layout.fragment_default) {

    private val viewBinding by viewBinding(FragmentDefaultBinding::bind)

    override fun initViewByScreen(screen: Screen) {

    }
}