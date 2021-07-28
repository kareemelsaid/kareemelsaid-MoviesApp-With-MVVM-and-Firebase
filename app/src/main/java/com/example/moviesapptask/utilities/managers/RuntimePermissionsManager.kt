package com.example.moviesapptask.utilities.managers

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

interface RuntimePermissionsManagerInterface {

    fun withActivity(context: Context): RuntimePermissionsManagerInterface

    fun requestPermissions(
        vararg permissions: String,
        requestCode: Int,
        requestPermissionsResult: (Boolean, Int) -> Unit
    )
}

class RuntimePermissionsManager : RuntimePermissionsManagerInterface {

    private var context: Context? = null

    override fun withActivity(context: Context): RuntimePermissionsManagerInterface {
        this.context = context

        return this
    }

    override fun requestPermissions(
        vararg permissions: String,
        requestCode: Int,
        requestPermissionsResult: (Boolean, Int) -> Unit
    ) {
        if (arePermissionsGranted(permissions)) {
            requestPermissionsResult.invoke(true, requestCode)
        } else {
            context?.let {
                ActivityCompat.requestPermissions(it as Activity, permissions, requestCode)
            }

            requestPermissionsResult.invoke(false, requestCode)
        }
    }

    private fun arePermissionsGranted(permissions: Array<out String>): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return permissions.count { context?.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED } == 0
        }

        return true
    }
}