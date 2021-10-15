package com.mobile.practice.longwork

import android.content.Context
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager


class WorkRequest(context: Context) {

    //Create charging constraints
    val chargingConstraints = Constraints.Builder()
        .setRequiresCharging(true)
        .build()


    //Create a WorkRequest to blur the image
    val blurWorkRequest = OneTimeWorkRequestBuilder<BlurWorker>()
        .setConstraints(chargingConstraints)
        .build()

    var workManager =
        WorkManager.getInstance(context).enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))

    /* var workManager1 = WorkManager.getInstance(context)
         .beginWith(imageFilter, imageCompress, imageupload)
         //if  anny off the returns output will be passed to  below methods
         .then(compresee)
         .then(upload)
         .enqueue()*/


}

