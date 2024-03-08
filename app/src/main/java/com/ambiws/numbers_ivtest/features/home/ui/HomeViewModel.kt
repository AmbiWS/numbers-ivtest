package com.ambiws.numbers_ivtest.features.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ambiws.numbers_ivtest.base.BaseViewModel
import com.ambiws.numbers_ivtest.features.home.ui.list.HistoryItem
import com.ambiws.numbers_ivtest.features.numbers.domain.interactor.NumbersInteractor
import com.ambiws.numbers_ivtest.features.numbers.domain.model.toItem
import com.ambiws.numbers_ivtest.features.numbers.ui.model.NumbersParams
import com.ambiws.numbers_ivtest.utils.extensions.mutable
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val numbersInteractor: NumbersInteractor
) : BaseViewModel() {

    val historyLiveData: LiveData<List<HistoryItem>> = MutableLiveData()

    init {
        loadHistory()
    }

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

    private fun loadHistory() {
        launch {
            historyLiveData.mutable().value = withContext(ioContext) {
                numbersInteractor.getSearchHistory().map {
                    it.toItem()
                }
            }
        }
    }
}
