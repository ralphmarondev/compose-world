package com.ralphmarondev.core.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

// NOTE: Call this on app launch!
fun scheduleAppWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<AppWorker>(
        1, TimeUnit.MINUTES // runs every 1 minute
    ).setConstraints(
        Constraints.Builder()
            .setRequiresBatteryNotLow(true) // only runs if batter is not low
            .build()
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "AppWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
}