package com.ambiws.numbers_ivtest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambiws.numbers_ivtest.base.navigation.NavigationCommand
import com.ambiws.numbers_ivtest.base.navigation.ViewModelNavigation
import com.ambiws.numbers_ivtest.core.network.adapters.ExceptionParser
import com.ambiws.numbers_ivtest.core.network.adapters.model.NetworkErrorData
import com.ambiws.numbers_ivtest.core.network.adapters.model.exceptions.UnauthorizedServerError
import com.ambiws.numbers_ivtest.utils.loge
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent {

    /*
     *  Navigation
     */

    protected val navigation by lazy { ViewModelNavigation() }
    val navigationCommand: LiveData<NavigationCommand> by lazy { navigation.navigationCommand }

    fun navigateBack(hideKeyboard: Boolean = true) = navigation.navigateBack(hideKeyboard)

    /*
     *  Data
     */

    private val exceptionParser: ExceptionParser by inject()
    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loge(throwable.message, throwable)
        // Firebase.crashlytics.recordException(throwable)
        setError(throwable)
    }

    protected val mainContext: CoroutineContext = Dispatchers.Main
    protected val ioContext: CoroutineContext = Dispatchers.IO

    private val _stateLiveEvent: MutableLiveData<UiState> = MutableLiveData()
    val stateLiveEvent: LiveData<UiState> = _stateLiveEvent

    protected fun setError(throwable: Throwable) {
        when (throwable) {
            is UnauthorizedServerError -> {
                // TODO implement logout or authorizer interceptor
                navigation.navigateBackToStart(hideKeyboard = true)
            }
            else -> {
                if (coroutineContext == mainContext) {
                    _stateLiveEvent.value = UiState.Error(exceptionParser.parseError(throwable))
                } else {
                    _stateLiveEvent.postValue(UiState.Error(exceptionParser.parseError(throwable)))
                }
            }
        }
    }

    /*
     *  Core
     */

    protected fun launch(
        coroutineContext: CoroutineContext = ioContext,
        showDefaultLoader: Boolean = false,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        // TODO check behaviour on collect (endless loading possible)
        return viewModelScope.launch(context = coroutineContext + defaultExceptionHandler) {
            if (showDefaultLoader) {
                if (coroutineContext == mainContext) {
                    _stateLiveEvent.value = UiState.Loading
                } else {
                    _stateLiveEvent.postValue(UiState.Loading)
                }
            }
            this.block()
            if (coroutineContext == mainContext) {
                _stateLiveEvent.value = UiState.Success
            } else {
                _stateLiveEvent.postValue(UiState.Success)
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Error(val error: NetworkErrorData) : UiState()
}
