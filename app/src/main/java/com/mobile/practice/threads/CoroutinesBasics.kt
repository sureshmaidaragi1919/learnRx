package com.mobile.practice.threads

import kotlinx.coroutines.*




    //sampleStart
    fun main() =
        repeat(100_000) { // launch a lot of coroutines
            Thread {
                Thread.sleep(5000L)
                print(".")
            }
        }



suspend fun doMyJob() = coroutineScope {
    val jobs = mutableListOf<Job>()
    for (i in 0..10 ){
        jobs += launch {
            println(i)
        }
    }
    jobs.forEach{ it.join() }
}

