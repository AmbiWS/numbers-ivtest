package com.ambiws.numbers_ivtest.features.numbers.ui

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.base.BaseFragment
import com.ambiws.numbers_ivtest.base.UiState
import com.ambiws.numbers_ivtest.databinding.FragmentNumbersBinding
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.utils.extensions.subscribe
import com.ambiws.numbers_ivtest.utils.loge

class NumbersFragment : BaseFragment<NumbersViewModel, FragmentNumbersBinding>(
    FragmentNumbersBinding::inflate
) {

    private val args by navArgs<NumbersFragmentArgs>()

    override fun setupUi() {
        with(args.numberParams) {
            timestamp?.let {
                binding.toolbar.title = getString(R.string.number_details_title, number)
                viewModel.getFactFromDb(it)
            } ?: number?.let {
                binding.toolbar.title = getString(R.string.number_details_title, it)
                viewModel.getNumberFact(it)
            } ?: kotlin.run {
                binding.toolbar.title = getString(R.string.generating)
                viewModel.getRandomNumberFact()
            }
        }
    }

    override fun setupListeners() {
        with(binding) {
            toolbar.ivLeftAction.setOnClickListener {
                viewModel.navigateBack()
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        subscribe(viewModel.numberFact) {
            binding.tvFacts.text = it
            val currentNumber = try {
                it.substring(0, it.indexOf(' ')).toInt()
            } catch (e: Exception) {
                loge("Impossible to save current fact")
                return@subscribe
            }
            binding.toolbar.title = getString(R.string.number_details_title, currentNumber)
            viewModel.saveFact(
                FactData(
                    timestamp = System.currentTimeMillis(),
                    number = currentNumber,
                    fact = it,
                )
            )
        }
        subscribe(viewModel.localNumberFact) {
            binding.tvFacts.text = it
        }
        subscribe(viewModel.stateLiveEvent) { state ->
            binding.loader.isVisible = state == UiState.Loading
        }
    }
}
