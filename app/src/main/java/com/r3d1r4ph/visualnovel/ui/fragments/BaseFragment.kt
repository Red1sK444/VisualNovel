package com.r3d1r4ph.visualnovel.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import androidx.viewbinding.ViewBinding
import com.r3d1r4ph.visualnovel.R
import com.r3d1r4ph.visualnovel.domain.Screen
import com.r3d1r4ph.visualnovel.domain.ScreenTypeEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes fragmentIdRes: Int) : Fragment(fragmentIdRes) {

    companion object {
        private const val DRAWABLE = "drawable"
    }

    protected open val viewModel by viewModels<ScreenViewModel>()
    protected abstract val viewBinding: ViewBinding
    protected abstract val args: NavArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        viewModel.getScreenById(getScreenId())
    }

    protected abstract fun getScreenId(): Int

    private fun initObservers() = with(viewModel) {
        screen.observe(viewLifecycleOwner, ::initViewByScreen)
        exception.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.load_screen_exception, Toast.LENGTH_SHORT)
                .show()
        }
        openNextScreen.observe(viewLifecycleOwner) {
            navigateByDirection(
                getDirectionByScreenType(it.screenId, it.name, it.screenType)
            )
        }
    }

    protected open fun initViewByScreen(screen: Screen) = with(viewBinding) {
        val resId =
            resources.getIdentifier(screen.background, DRAWABLE, requireActivity().packageName)
        root.setBackgroundResource(resId)
    }

    protected fun getStringByResourceName(resourceName: String, parameter: String? = null): String {
        val idField = R.string::class.java.getDeclaredField(resourceName)
        return if (parameter == null) {
            getString(idField.getInt(idField))
        } else {
            getString(idField.getInt(idField), parameter)
        }
    }

    protected fun navigateByScreenId(screenId: Int, name: String? = null) = with(viewModel) {
        openNextScreen(screenId, name)
    }

    private fun navigateByDirection(navDirections: NavDirections?) {
        navDirections?.let { action ->
            findNavController().apply {
                navigate(action)
            }
        }
    }

    protected abstract fun getDirectionByScreenType(
        screenId: Int,
        name: String? = null,
        screenType: ScreenTypeEnum
    ): NavDirections?
}