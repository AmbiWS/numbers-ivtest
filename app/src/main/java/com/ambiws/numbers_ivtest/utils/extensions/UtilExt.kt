package com.ambiws.numbers_ivtest.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

val <T : Any> T.className: String
    get() = this::class.java.simpleName

fun Fragment.showUnderDevelopmentToast() {
    Toast.makeText(requireContext(), "Under development", Toast.LENGTH_SHORT).show()
}
