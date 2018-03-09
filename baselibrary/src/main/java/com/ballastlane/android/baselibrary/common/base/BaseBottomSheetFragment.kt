package com.ballastlane.android.baselibrary.common.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import com.ballastlane.android.baselibrary.common.base.busevents.utils.EventGhost
import com.ballastlane.android.baselibrary.common.base.lifecycle.EventBusLifeCycleObserver
import com.ballastlane.android.baselibrary.common.base.lifecycle.RxLifeObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseBottomSheetFragment : BottomSheetDialogFragment(), LifecycleRegistryOwner {

    private val TAG = "TAG_${BaseBottomSheetFragment::class.java.simpleName}"


    private val lifecycleRegistry = LifecycleRegistry(this)
    private val rxLifeObserver = RxLifeObserver()
    private val busLifeObserver = EventBusLifeCycleObserver(this)

    val disposables: CompositeDisposable?
        get() = rxLifeObserver.disposables

    val disposablesForever: CompositeDisposable?
        get() = rxLifeObserver.disposablesForever

    override fun getLifecycle(): LifecycleRegistry {
        return this.lifecycleRegistry
    }

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
    }

    protected abstract fun initVars()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVars()
        initLifeCycleObservers()
    }

    private fun initLifeCycleObservers() {
        lifecycle.addObserver(rxLifeObserver)
        lifecycle.addObserver(busLifeObserver)
    }

    @Subscribe
    fun onEventGhost(event: EventGhost) {
    }
}
