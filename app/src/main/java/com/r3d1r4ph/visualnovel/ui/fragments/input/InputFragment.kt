package com.r3d1r4ph.visualnovel.ui.fragments.input

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentInputBinding
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.ui.fragments.BaseFragment
import com.r3d1r4ph.visualnovel.utils.ResultWrapper

class InputFragment : BaseFragment(R.layout.fragment_input) {

    override val viewBinding by viewBinding(FragmentInputBinding::bind)
    override val viewModel by viewModels<InputScreenViewModel>()

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
            when (result) {
                is ResultWrapper.Success ->
                    navigateByScreenId(
                        screenId = screen.actions[1].toScreen,
                        name = result.value
                    )
                is ResultWrapper.Failure -> with(inputTextInputLayout) {
                    error = resources.getString(R.string.empty_name)
                    isErrorEnabled = true
                }
            }
        }

        inputConfirmButton.setOnClickListener {
            viewModel.validateName(inputTextInputEditText.text.toString())
        }
    }
}