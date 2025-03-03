package com.ralphmarondev.core.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ralphmarondev.core.notification.AppNotification

class AppWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val notificationHelper = AppNotification(applicationContext)
        notificationHelper.showNotification(
            title = "Reminder",
            message = "You look cute when smiling, so please, don't forget to smile everyday!"
        )
        return Result.success()
    }
}