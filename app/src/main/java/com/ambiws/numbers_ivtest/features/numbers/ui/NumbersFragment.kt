package com.ambiws.numbers_ivtest.features.numbers.ui

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.base.BaseFragment
import com.ambiws.numbers_ivtest.base.UiState
import com.ambiws.numbers_ivtest.databinding.FragmentNumbersBinding
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.utils.Const
import com.ambiws.numbers_ivtest.utils.extensions.subscribe
import kotlin.properties.Delegates
import kotlin.random.Random

class NumbersFragment : BaseFragment<NumbersViewModel, FragmentNumbersBinding>(
    FragmentNumbersBinding::inflate
) {

    private val args by navArgs<NumbersFragmentArgs>()
    private var currentNumber by Delegates.notNull<Int>()

    override fun setupUi() {
        with(binding) {
            currentNumber = if (args.numberParams.number != null) {
                args.numberParams.number!!
            } else {
                // Only values '0..1000' available for random number
                Random.nextInt(0, Const.MAX_RANDOM_NUMBER_UNTIL)
            }
            toolbar.setTitle(getString(R.string.number_details_title, currentNumber))
            viewModel.getNumberFact(currentNumber)
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
            viewModel.saveFact(
                FactData(
                    timestamp = System.currentTimeMillis(),
                    number = currentNumber,
                    fact = it,
                )
            )
        }
        subscribe(viewModel.stateLiveEvent) { state ->
            binding.loader.isVisible = state == UiState.Loading
        }
    }
}
