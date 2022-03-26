package com.r3d1r4ph.visualnovel.ui.fragments

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentDefaultBinding
import com.r3d1r4ph.visualnovel.domain.Screen

class DefaultFragment : BaseFragment(R.layout.fragment_default) {

    override val viewBinding by viewBinding(FragmentDefaultBinding::bind)

    override fun initViewByScreen(screen: Screen) = with(viewBinding) {
        super.initViewByScreen(screen)

        defaultMessageTextView.text =
            if (screen.id == 3) {
                val name: String = arguments?.getString(NAME_ID) ?: getString(R.string.empty)
                String.format(screen.message, name)
            } else {
                screen.message
            }

        actionGroup.referencedIds.forEachIndexed { index, id ->
            val actionButton = root.findViewById<MaterialButton>(id)
            if (screen.actions.size > index) {
                with(actionButton) {
                    visibility = View.VISIBLE

                    text = screen.actions[index].message
                    setOnClickListener {
                        navigateByScreenId(screen.actions[index].toScreen)
                    }
                }
            }
        }

    }
}