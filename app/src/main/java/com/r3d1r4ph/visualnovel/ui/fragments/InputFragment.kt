package com.r3d1r4ph.visualnovel.ui.fragments

import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentInputBinding
import com.r3d1r4ph.visualnovel.domain.Screen

class InputFragment : BaseFragment(R.layout.fragment_input) {

    private val viewBinding by viewBinding(FragmentInputBinding::bind)

    override fun initViewByScreen(screen: Screen) {

    }
}