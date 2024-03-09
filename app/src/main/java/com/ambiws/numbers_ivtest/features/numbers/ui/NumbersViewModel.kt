package com.ambiws.numbers_ivtest.features.numbers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ambiws.numbers_ivtest.base.BaseViewModel
import com.ambiws.numbers_ivtest.features.numbers.domain.interactor.NumbersInteractor
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.utils.extensions.mutable
import com.ambiws.numbers_ivtest.utils.logd
import kotlinx.coroutines.withContext

class NumbersViewModel(
    private val numbersInteractor: NumbersInteractor
) : BaseViewModel() {

    val numberFact: LiveData<String> = MutableLiveData()
    val localNumberFact: LiveData<String> = MutableLiveData()

    fun getNumberFact(number: Int) {
        launch(showDefaultLoader = true) {
            numberFact.mutable().value = withContext(ioContext) {
                numbersInteractor.getNumberFact(number)
            }
        }
    }

    fun getRandomNumberFact() {
        launch(showDefaultLoader = true) {
            numberFact.mutable().value = withContext(ioContext) {
                numbersInteractor.getRandomNumberFact()
            }
        }
    }

    fun getFactFromDb(timestamp: Long) {
        launch(showDefaultLoader = true) {
            localNumberFact.mutable().value = withContext(ioContext) {
                numbersInteractor.getFactByTimestamp(timestamp)
            }
        }
    }

    fun saveFact(fact: FactData) {
        launch {
            logd("Saving fact: $fact")
            numbersInteractor.insertFact(fact)
        }
    }
}
