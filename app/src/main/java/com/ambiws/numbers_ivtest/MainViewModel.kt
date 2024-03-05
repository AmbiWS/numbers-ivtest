package com.ambiws.numbers_ivtest

import androidx.lifecycle.ViewModel
import com.ambiws.numbers_ivtest.utils.SingleLiveEvent

class MainViewModel : ViewModel() {

    val startDestinationEvent = SingleLiveEvent<Int>()

    fun initStartDestination() {
        startDestinationEvent.value = R.id.homeFragment
    }
}
