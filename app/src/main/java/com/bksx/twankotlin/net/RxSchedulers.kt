package com.bksx.twankotlin.net

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author JoneChen
 * @Date 2019\7\3 0003-11:02
 */
object RxSchedulers {

    /**
     * [Observable] 线程切换
     * [observableScheduler] 是被观察者所在的线程,默认为 [Schedulers.io]
     * [observerScheduler] 是观察者所在的线程,默认为 [AndroidSchedulers.mainThread]
     * return [ObservableTransformer]
     */
    fun <T> observableTransformer(observableScheduler:Scheduler = Schedulers.io(),observerScheduler:Scheduler = AndroidSchedulers.mainThread())
    :ObservableTransformer<T,T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .unsubscribeOn(observableScheduler)
            .observeOn(observerScheduler)
    }

    /**
     * [Flowable] 线程切换
     * [flowableScheduler] 发射器所在线程，默认为 [Schedulers.io]
     * [observeScheduler] 接收器所在线程，默认为 [AndroidSchedulers.mainThread]
     * return [FlowableTransformer]
     */
    fun <T> flowableTransfromer(flowableScheduler:Scheduler = Schedulers.io(),observerScheduler: Scheduler = AndroidSchedulers.mainThread())
    :FlowableTransformer<T,T> = FlowableTransformer {
        it.subscribeOn(flowableScheduler)
            .unsubscribeOn(flowableScheduler)
            .observeOn(observerScheduler)
    }
}