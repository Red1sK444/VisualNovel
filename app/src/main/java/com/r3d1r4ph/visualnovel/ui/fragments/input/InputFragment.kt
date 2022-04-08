package com.r3d1r4ph.visualnovel.ui.fragments.input

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentInputBinding
import com.r3d1r4ph.visualnovel.domain.models.Screen
import com.r3d1r4ph.visualnovel.domain.models.ScreenTypeEnum
import com.r3d1r4ph.visualnovel.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : BaseFragment(R.layout.fragment_input) {

    override val viewBinding by viewBinding(FragmentInputBinding::bind)
    override val viewModel by viewModels<InputScreenViewModel>()
    override val args: InputFragmentArgs by navArgs()

    override fun initViewByScreen(screen: Screen) = with(viewBinding) {
        super.initViewByScreen(screen)

        inputTextInputEditText.addTextChangedListener {
            inputTextInputLayout.error = resources.getString(R.string.empty)
            inputTextInputLayout.isErrorEnabled = false
        }

        inputMessageTextView.text = getStringByResourceName(screen.message)

        inputTextInputEditText.hint = getStringByResourceName(screen.actions[0].message)
        inputConfirmButton.text = getStringByResourceName(screen.actions[1].message)

        viewModel.validation.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                navigateByScreenId(
                    screenId = screen.actions[1].toScreen,
                    name = result.getOrNull()
                )
            } else {
                inputTextInputLayout.error = resources.getString(R.string.empty_name)
                inputTextInputLayout.isErrorEnabled = true
            }
        }

        inputConfirmButton.setOnClickListener {
            viewModel.validateName(inputTextInputEditText.text.toString())
        }
    }

    override fun getDirectionByScreenType(
        screenId: Int,
        name: String?,
        screenType: ScreenTypeEnum
    ): NavDirections? {
        return when (screenType) {
            ScreenTypeEnum.PREVIEW -> null
            ScreenTypeEnum.INPUT -> null
            ScreenTypeEnum.DEFAULT -> InputFragmentDirections.actionInputFragmentToDefaultFragment(
                screenId,
                name
            )
        }
    }

    override fun getScreenId() = args.screenId
}