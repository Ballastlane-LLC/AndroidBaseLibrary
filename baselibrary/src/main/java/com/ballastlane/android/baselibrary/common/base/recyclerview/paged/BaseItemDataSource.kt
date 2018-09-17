package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.paging.ItemKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseItemDataSource<ITEM : DiffCallback.DiffItemUniqueId<TYPE_ID>, TYPE_ID, API>(
        private val api: API,
        private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<TYPE_ID, ITEM>() {

    private val TAG = "TAG_${BaseItemDataSource::class.java.simpleName}"

    var page = 1

    abstract var pageTotal: Long?

    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d("testing here")
                    }, { throwable -> Timber.e(throwable.message) }))
        }
    }

    private fun nextPage() {
        page++
    }

    override fun getKey(item: ITEM): TYPE_ID {
        return item.unique!!
    }

    override fun loadBefore(params: LoadParams<TYPE_ID>, callback: LoadCallback<ITEM>) {
        // ignored, since we only ever append to our initial load
    }

}