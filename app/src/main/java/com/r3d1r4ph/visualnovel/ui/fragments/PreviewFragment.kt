package com.r3d1r4ph.visualnovel.ui.fragments

import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentPreviewBinding
import com.r3d1r4ph.visualnovel.domain.Screen


class PreviewFragment : BaseFragment(R.layout.fragment_preview) {

    override val viewBinding by viewBinding(FragmentPreviewBinding::bind)

    override fun initViewByScreen(screen: Screen) = with(viewBinding) {
        super.initViewByScreen(screen)

        previewTitleTextView.text = screen.message
        previewButton.text = screen.actions[0].message

        previewButton.setOnClickListener {
            navigateByScreenId(screen.actions[0].toScreen)
        }
    }
}