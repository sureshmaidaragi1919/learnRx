package com.mobile.practice.longwork

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BlurWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        //long running background tasks, asyncrohous in Background thread
        return null!!
    }
}