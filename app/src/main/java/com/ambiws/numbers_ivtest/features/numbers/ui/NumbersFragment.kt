package com.ambiws.numbers_ivtest.features.numbers.ui

import androidx.navigation.fragment.navArgs
import com.ambiws.numbers_ivtest.R
import com.ambiws.numbers_ivtest.base.BaseFragment
import com.ambiws.numbers_ivtest.base.EmptyViewModel
import com.ambiws.numbers_ivtest.databinding.FragmentNumbersBinding
import com.ambiws.numbers_ivtest.utils.Const
import kotlin.random.Random

class NumbersFragment : BaseFragment<EmptyViewModel, FragmentNumbersBinding>(
    FragmentNumbersBinding::inflate
) {

    private val args by navArgs<NumbersFragmentArgs>()

    override fun setupUi() {
        with(binding) {
            val number = if (args.numberParams.number != null) {
                args.numberParams.number
            } else {
                // Only values '0..1000' available for random number
                Random.nextInt(0, Const.MAX_RANDOM_NUMBER_UNTIL)
            }
            toolbar.setTitle(getString(R.string.number_details_title, number))
        }
    }

    override fun setupListeners() {
        with(binding) {
            toolbar.ivLeftAction.setOnClickListener {
                viewModel.navigateBack()
            }
        }
    }
}
