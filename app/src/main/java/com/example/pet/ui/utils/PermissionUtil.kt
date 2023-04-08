package com.example.pet.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object PermissionUtil {

    private  var resultLauncher: ActivityResultLauncher<Intent>? = null
    private  var oldResultLauncher: ActivityResultLauncher<String>? = null

    fun hasPermissions(context: Context): Boolean {
        return when {
            Build.VERSION.SDK_INT >= 33 -> {
                (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED)
            }
            else -> {
                true
            }
        }
    }
    fun registerLauncher(launcher: ActivityResultLauncher<Intent>) {
        resultLauncher = launcher
    }

    fun oldRegisterLauncher(launcher: ActivityResultLauncher<String>) {
        oldResultLauncher = launcher
    }
    fun requestPermissions(
        activity: Activity
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.addCategory("")
                intent.data = Uri.parse(String.format("package:%s", activity.packageName))
                resultLauncher?.launch(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            oldResultLauncher?.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}