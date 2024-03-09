package com.ambiws.numbers_ivtest.features.home.ui

import com.ambiws.numbers_ivtest.base.BaseViewModel
import com.ambiws.numbers_ivtest.features.home.ui.list.HistoryItemModel
import com.ambiws.numbers_ivtest.features.numbers.domain.interactor.NumbersInteractor
import com.ambiws.numbers_ivtest.features.numbers.domain.model.toItem
import com.ambiws.numbers_ivtest.features.numbers.ui.model.NumbersParams
import com.ambiws.numbers_ivtest.utils.SingleLiveEvent
import com.ambiws.numbers_ivtest.utils.extensions.mutable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val numbersInteractor: NumbersInteractor
) : BaseViewModel() {

    private val _historyFlow = MutableStateFlow<List<HistoryItemModel>>(emptyList())
    val historyFlow: StateFlow<List<HistoryItemModel>> = _historyFlow

    val listUpdateLiveEvent = SingleLiveEvent<Boolean>()

    init {
        loadHistory()
    }

    fun navigateToFacts(number: Int?, timestamp: Long?) {
        try {
            if (number != null) {
                navigation.navigate(
                    HomeFragmentDirections.actionHomeFragmentToNumbersFragment(
                        NumbersParams(number, timestamp)
                    )
                )
            } else {
                setError(
                    IllegalStateException("Number field is empty")
                )
            }
        } catch (e: IllegalStateException) {
            setError(e)
        }
    }

    fun navigateToRandomFact() {
        try {
            navigation.navigate(
                HomeFragmentDirections.actionHomeFragmentToNumbersFragment(
                    NumbersParams(null, null)
                )
            )
        } catch (e: IllegalStateException) {
            setError(e)
        }
    }

    fun callListUpdated() {
        listUpdateLiveEvent.mutable().postValue(true)
    }

    private fun loadHistory() {
        launch {
            withContext(ioContext) {
                numbersInteractor.getSearchHistory().map {
                    it.map { it.toItem() }
                }
            }.collect {
                _historyFlow.value = it
            }
        }
    }
}
