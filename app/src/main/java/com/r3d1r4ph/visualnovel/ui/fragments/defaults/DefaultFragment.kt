package com.r3d1r4ph.visualnovel.ui.fragments.defaults

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentDefaultBinding
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum
import com.r3d1r4ph.visualnovel.ui.fragments.BaseFragment
import com.r3d1r4ph.visualnovel.ui.fragments.input.InputFragmentDirections

class DefaultFragment : BaseFragment(R.layout.fragment_default) {

    override val viewBinding by viewBinding(FragmentDefaultBinding::bind)
    override val args: DefaultFragmentArgs by navArgs()

    override fun initViewByScreen(screen: Screen) = with(viewBinding) {
        super.initViewByScreen(screen)

        defaultMessageTextView.text =
            if (screen.id == 3) {
                val name: String = args.name ?: getString(R.string.empty)
                getStringByResourceName(screen.message, name)
            } else {
                getStringByResourceName(screen.message)
            }

        actionGroup.referencedIds.forEachIndexed { index, id ->
            val actionButton = root.findViewById<MaterialButton>(id)
            if (screen.actions.size > index) {
                with(actionButton) {
                    visibility = View.VISIBLE

                    text = getStringByResourceName(screen.actions[index].message)
                    setOnClickListener {
                        navigateByScreenId(screen.actions[index].toScreen)
                    }
                }
            }
        }
    }

    override fun getDirectionByScreenType(
        screenId: Int,
        name: String?,
        screenType: ScreenTypeEnum
    ): NavDirections? {
        return when (screenType) {
            ScreenTypeEnum.PREVIEW -> DefaultFragmentDirections.actionDefaultFragmentToPreviewFragment(
                screenId
            )
            ScreenTypeEnum.INPUT -> null
            ScreenTypeEnum.DEFAULT -> DefaultFragmentDirections.actionDefaultFragmentSelf(screenId)
        }
    }

    override fun getScreenId() = args.screenId
}