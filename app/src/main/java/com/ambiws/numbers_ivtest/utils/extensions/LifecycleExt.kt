package com.ambiws.numbers_ivtest.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> AppCompatActivity.subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
    liveData?.observe(this) {
        if (it != null) {
            onNext(it)
        }
    }
}

fun <T> AppCompatActivity.subscribe(channel: (Flow<T>)?, onNext: (t: T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            channel?.collectLatest {
                if (it != null) {
                    onNext(it)
                }
            }
        }
    }
}

fun <T> Fragment.subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
    liveData?.observe(viewLifecycleOwner) {
        if (it != null) {
            onNext(it)
        }
    }
}

fun <T> Fragment.subscribe(flow: (Flow<T?>)?, onNext: (t: T) -> Unit) {
    flow ?: return
    viewLifecycleOwner.lifecycle.coroutineScope.launch {
        flow.collect {
            if (it != null) onNext(it)
        }
    }
}

fun <T> Fragment.subscribeNullable(liveData: LiveData<T>?, onNext: (t: T?) -> Unit) {
    liveData ?: return
    liveData.observe(viewLifecycleOwner, Observer { onNext(it) })
}

fun <T> LiveData<T>.mutable(): MutableLiveData<T> =
    (this as? MutableLiveData) ?: throw IllegalArgumentException("LiveData is not mutable")
