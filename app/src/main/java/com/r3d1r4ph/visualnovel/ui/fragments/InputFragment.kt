package com.r3d1r4ph.visualnovel.ui.fragments

import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.databinding.FragmentInputBinding
import com.r3d1r4ph.visualnovel.domain.Screen

class InputFragment : BaseFragment(R.layout.fragment_input) {

    override val viewBinding by viewBinding(FragmentInputBinding::bind)

    override fun initViewByScreen(screen: Screen) = with(viewBinding) {
        super.initViewByScreen(screen)

        inputTextInputEditText.addTextChangedListener {
            inputTextInputLayout.error = resources.getString(R.string.empty)
            inputTextInputLayout.isErrorEnabled = false
        }

        inputMessageTextView.text = getStringByResourceName(screen.message)

        inputTextInputEditText.hint = getStringByResourceName(screen.actions[0].message)
        inputConfirmButton.text = getStringByResourceName(screen.actions[1].message)

        inputConfirmButton.setOnClickListener {
            val name = inputTextInputEditText.text.toString()

            if (name.isEmpty()) {
                with(inputTextInputLayout) {
                    error = resources.getString(R.string.empty_name)
                    isErrorEnabled = true
                    return@setOnClickListener
                }
            }

            navigateByScreenId(
                screenId = screen.actions[1].toScreen,
                name = name
            )
        }
    }
}