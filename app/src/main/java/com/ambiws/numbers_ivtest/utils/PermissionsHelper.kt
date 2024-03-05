package com.ambiws.numbers_ivtest.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import java.util.ArrayDeque

object PermissionsHelper {

    fun isPermissionsGranted(permissions: List<String>, context: Context): Boolean {
        val permissionsSource = ArrayDeque<String>()
        permissionsSource.addAll(permissions)
        while (permissionsSource.size > 0) {
            val permission = permissionsSource.pop()
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun isPermissionGranted(permission: String, context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}
