package com.ambiws.numbers_ivtest.features.home.ui

import com.ambiws.numbers_ivtest.base.BaseViewModel
import com.ambiws.numbers_ivtest.features.numbers.ui.model.NumbersParams

class HomeViewModel : BaseViewModel() {

    fun navigateToFacts(number: Int?) {
        if (number != null) {
            navigation.navigate(
                HomeFragmentDirections.actionHomeFragmentToNumbersFragment(
                    NumbersParams(number)
                )
            )
        } else {
            setError(
                IllegalStateException("Number field is empty")
            )
        }
    }

    fun navigateToRandomFact() {
        navigation.navigate(
            HomeFragmentDirections.actionHomeFragmentToNumbersFragment(
                NumbersParams(null)
            )
        )
    }
}
