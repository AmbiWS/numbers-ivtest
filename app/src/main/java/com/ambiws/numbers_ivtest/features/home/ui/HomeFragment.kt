package com.ambiws.numbers_ivtest.features.home.ui

import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.base.BaseFragment
import com.ambiws.numbers_ivtest.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun setupListeners() {
        with(binding) {
            etNumber.setOnFocusChangeListener { _, hasFocus ->
                setNumbersHint(hasFocus)
            }
            btnGetFact.setOnClickListener {
                viewModel.navigateToFacts(
                    try {
                        etNumber.text.toString().toInt()
                    } catch (e: Exception) {
                        null
                    }
                )
            }
            btnGetRandomFact.setOnClickListener {
                viewModel.navigateToRandomFact()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setNumbersHint(hasFocus = (binding.etNumber.text?.length ?: 0) > 0)
    }

    private fun setNumbersHint(hasFocus: Boolean) {
        binding.tilNumber.hint = if (hasFocus) {
            getString(R.string.number_hint_focused)
        } else {
            getString(R.string.number_hint)
        }
    }
}
