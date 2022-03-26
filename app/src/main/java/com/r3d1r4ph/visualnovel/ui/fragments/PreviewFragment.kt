package com.r3d1r4ph.visualnovel.ui.fragments

import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentPreviewBinding
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.ui.fragments.BaseFragment


class PreviewFragment : BaseFragment(R.layout.fragment_preview) {

    private val viewBinding by viewBinding(FragmentPreviewBinding::bind)

    override fun initViewByScreen(screen: Screen) {

    }
}