package com.ambiws.numbers_ivtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.base.navigation.NavigationCommandHandler
import com.google.android.material.snackbar.Snackbar
import com.ambiws.numbers_ivtest.utils.extensions.className
import com.ambiws.numbers_ivtest.utils.extensions.subscribe
import com.ambiws.numbers_ivtest.utils.logd
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import java.lang.reflect.ParameterizedType


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    @Suppress("UNCHECKED_CAST")
    protected open val viewModel: VM by lazy {
        viewModelForClass(
            clazz = ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).kotlin
        ).value
    }

    protected open val navigationCommandHandler =
        NavigationCommandHandler(navControllerDefinition = { findNavController() })

    open fun setupUi() {
        logd(getString(R.string.setup_stage, "Ui", className))
    }

    open fun setupListeners() {
        logd(getString(R.string.setup_stage, "Listeners", className))
    }

    @CallSuper
    open fun setupObservers() {
        logd(getString(R.string.setup_stage, "Observers", className))
        subscribe(viewModel.navigationCommand) { navigationCommand ->
            navigationCommandHandler.handle(requireActivity(), navigationCommand)
        }
        subscribe(viewModel.stateLiveEvent) { state ->
            when (state) {
                UiState.Loading -> {
                    // Nothing to do
                }
                UiState.Success -> {
                    // Nothing to do
                }
                is UiState.Error -> {
                    Snackbar.make(binding.root, state.error.message, Snackbar.LENGTH_SHORT)
                        .setTextColor(requireContext().getColor(R.color.lt_red))
                        .show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
