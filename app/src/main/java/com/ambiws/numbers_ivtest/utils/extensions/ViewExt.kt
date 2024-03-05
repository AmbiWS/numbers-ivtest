package com.ambiws.numbers_ivtest.utils.extensions

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator

fun View.animateFadeIn() {
    this.animate().alpha(1f)
        .setDuration(1000)
        .setInterpolator(DecelerateInterpolator())
        .start()
}

fun View.animateFadeOut() {
    this.animate().alpha(0f)
        .setDuration(1000)
        .setInterpolator(AccelerateInterpolator())
        .start()
}
