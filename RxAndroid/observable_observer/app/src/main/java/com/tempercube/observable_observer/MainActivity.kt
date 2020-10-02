package com.tempercube.observable_observer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // Buat sebuah observable
        val observable: Observable<Task> = Observable
            .fromIterable(DataSource.getTasks())
            .subscribeOn(Schedulers.io()) // dimana Observable akan dioperasikan
            .filter {
                Log.d("MainActivity", "filter, on thread : ${Thread.currentThread().name}")
                Thread.sleep(1000L)
                !it.isComplete
            }
            .observeOn(AndroidSchedulers.mainThread()) // dimana Observable akan notify

        observable.subscribe(object : Observer<Task> {
            override fun onComplete() {
                Log.d("MainActivity", "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("MainActivity", "onSubscribe, on thread : ${Thread.currentThread().name}")
            }

            override fun onNext(t: Task) {
                Log.d("MainActivity", "onNext : $t, on thread : ${Thread.currentThread().name}")
            }

            override fun onError(e: Throwable) {
                Log.d("MainActivity", "onError : ${e.message}")
            }
        })
    }
}
