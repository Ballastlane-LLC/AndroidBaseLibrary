package com.ballastlane.android.baselibrary.common.base

import android.app.Service
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import com.ballastlane.android.baselibrary.common.base.lifecycle.EventBusLifeCycleObserver
import com.ballastlane.android.baselibrary.common.base.lifecycle.RxLifeObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 8/28/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseService : Service(), LifecycleRegistryOwner {

    private val TAG = "TAG_${BaseService::class.java.simpleName}"

    private val mRegistry = LifecycleRegistry(this)

    private val busLifeObserver = EventBusLifeCycleObserver(this)

    private val rxLifeObserver = RxLifeObserver()

    override fun onCreate() {
        super.onCreate()
        initLifeCycleObservers()
    }

    override fun getLifecycle(): LifecycleRegistry {
        return this.mRegistry
    }

    val disposables: CompositeDisposable?
        get() = rxLifeObserver.disposables

    val disposablesForever: CompositeDisposable?
        get() = rxLifeObserver.disposablesForever

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
    }

    private fun initLifeCycleObservers() {
        lifecycle.addObserver(rxLifeObserver)
        lifecycle.addObserver(busLifeObserver)
    }
}