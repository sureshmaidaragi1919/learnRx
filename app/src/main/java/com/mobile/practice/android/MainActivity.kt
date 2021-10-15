package com.mobile.practice.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.practice.R
import com.mobile.practice.android.data.DataSource
import com.mobile.practice.android.model.Tasks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Thread.currentThread


//what are operators in rx java
// The purpose of operator is to take a given data set and transform it into observable data set.
//Some operators can also manipulate the data objects and transform them further.

//categorize the operators in rx java
//
//Create observables >>>>> create(), fromIterable(), fromArray(), just(), range(),repeat()
//Filter and Sorting observables >>>> filter(),  distinct(),  take(),takeLast(), skip()
//Transform emitted data into other types >>>> map(), flatMap(), switchMap(), buffer(), concatMap()


/*
* Flatmap operator does not maintain order
* */
class MainActivity : AppCompatActivity() {

    private var disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskObservable =

            Observable
                .fromIterable(DataSource.createTasksList())
                .filter(Predicate<Tasks> {
                    println("test  : ${Thread.currentThread().name}")
                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                        println(e)
                    }
                    return@Predicate it.isComplete
                })
                .subscribeOn(Schedulers.io())//worker thread assistant where the work should be done basically on io thread
                .observeOn(AndroidSchedulers.mainThread())


        taskObservable.subscribe(object :
            Observer<Tasks> { //observer subsribing to observable
            override fun onSubscribe(d: Disposable) {
                println("On onSubscribe: ")
                disposable.add(d)
            }

            override fun onNext(t: Tasks) {
                println("On onNext:  thread  name:  " + currentThread().name)
                println("On onNext:desc " + t.desc)

            }

            override fun onComplete() {
                println("On onComplete: ")
            }

            override fun onError(e: Throwable) {
                println("On onError: $e")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        disposable.dispose()
    }
}