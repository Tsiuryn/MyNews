package com.ts.alex.mynews.data.datasource.impl

import android.content.Context
import androidx.work.*
import com.ts.alex.mynews.data.WORK_NAME
import com.ts.alex.mynews.data.datasource.IJobDataSource
import com.ts.alex.mynews.data.workmanager.Manager
import java.util.concurrent.TimeUnit

class JobDataSource(private val context: Context) : IJobDataSource {

    override fun startJob(mls: Long) {
        val manager = WorkManager.getInstance(context)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val flexTime = mls - 5 * 60 * 1_000

        val myWorkRequest =
            PeriodicWorkRequestBuilder<Manager>(
                repeatInterval = mls,
                repeatIntervalTimeUnit = TimeUnit.MILLISECONDS,
                flexTimeInterval = flexTime,
                TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .addTag(WORK_NAME)
                .build()


        manager.enqueue(myWorkRequest)
    }

    override fun cancelJob() {
        WorkManager.getInstance(context).cancelAllWorkByTag(WORK_NAME)
    }
}