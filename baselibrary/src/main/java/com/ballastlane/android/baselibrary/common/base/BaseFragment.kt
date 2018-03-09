package com.ballastlane.android.baselibrary.common.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.View
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

abstract class BaseFragment : Fragment(), LifecycleRegistryOwner {

    private val mRegistry = LifecycleRegistry(this)
    private val rxLifeObserver = RxLifeObserver()
    private val busLifeObserver = EventBusLifeCycleObserver(this)

    val disposables: CompositeDisposable?
        get() = rxLifeObserver.disposables

    val disposablesForever: CompositeDisposable?
        get() = rxLifeObserver.disposablesForever

    override fun getLifecycle(): LifecycleRegistry {
        return this.mRegistry
    }

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
    }

    protected abstract fun initVars()

    @CallSuper
    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        initLifeCycleObservers()
        initVars()
    }

    private fun initLifeCycleObservers() {
        lifecycle.addObserver(rxLifeObserver)
        lifecycle.addObserver(busLifeObserver)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    protected abstract fun initViews()

    protected abstract fun initListeners()

    @Subscribe
    fun onEventGhost(event: EventGhost) {
    }

}
