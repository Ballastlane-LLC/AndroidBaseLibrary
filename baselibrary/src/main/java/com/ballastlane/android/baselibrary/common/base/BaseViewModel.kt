package com.ballastlane.android.baselibrary.common.base

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * @author Daniela Perez danielaperez@kogimobile.com on 2/5/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared()")
        compositeDisposable.clear()
    }

    companion object {

        val TAG = BaseViewModel::class.java.simpleName!!
    }
}
