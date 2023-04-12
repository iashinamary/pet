package com.example.pet.oneTimeWorkRequest

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pet.R
import com.example.pet.data.TaskRepo
import com.example.pet.ui.MainActivity
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TaskWorker(
    context: Context,
    private val params: WorkerParameters,
): CoroutineWorker(context, params), KoinComponent {
    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 1
    }



    private val repository: TaskRepo by inject()

    private val notificationManager: NotificationManager =
        context.getSystemService(NotificationManager::class.java)

    override suspend fun doWork(): Result {

        return try {

            val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.paw)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .build()
             repository.getTaskToNotificate(params.id.toString())?.let { task ->
                notificationManager.notify(task.id.toInt(), notification.also {
                    it.tickerText = task.name
                })
                repository.onShow(task.id)
            }



            Result.success()
        } catch (e: Exception){
            e.printStackTrace()
            Result.failure()
        }

    }

}