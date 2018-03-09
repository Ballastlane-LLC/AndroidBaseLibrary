package com.ballastlane.android.baselibrary.common.base.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class EventBusLifeCycleObserver(private val subscriber: Any) : LifecycleObserver {

    private val TAG = "TAG_${EventBusLifeCycleObserver::class.java.simpleName}"


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        EventBus.getDefault().register(subscriber)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        EventBus.getDefault().unregister(subscriber)
    }

}