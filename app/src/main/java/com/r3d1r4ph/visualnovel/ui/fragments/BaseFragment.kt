package com.r3d1r4ph.visualnovel.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypes
import com.r3d1r4ph.visualnovel.ui.ScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes fragmentIdRes: Int) : Fragment(fragmentIdRes) {

    companion object {
        const val SCREEN_ID = "Screen Id"
    }

    private val viewModel by viewModels<ScreenViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val screenId: Int = arguments?.getInt(SCREEN_ID) ?: 1
        viewModel.getScreenById(screenId)

        viewModel.screen.observe(viewLifecycleOwner, ::initViewByScreen)
        viewModel.exception.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.load_screen_exception, Toast.LENGTH_SHORT)
                .show()
        }
    }

    protected abstract fun initViewByScreen(screen: Screen)

    protected fun navigateByScreenId(screenId: Int) {
        determineFragmentByType(screenId)?.let { fragmentId ->
            findNavController().apply {
                popBackStack()
                navigate(fragmentId, args = Bundle().apply {
                    putInt(SCREEN_ID, screenId)
                })
            }
        }
    }

    @IdRes
    private fun determineFragmentByType(screenId: Int): Int? {
        val type = viewModel.getScreenType(screenId) ?: return null
        return when (type) {
            ScreenTypes.PREVIEW -> R.id.previewFragment
            ScreenTypes.INPUT -> R.id.inputFragment
            ScreenTypes.DEFAULT -> R.id.defaultFragment
        }
    }
}