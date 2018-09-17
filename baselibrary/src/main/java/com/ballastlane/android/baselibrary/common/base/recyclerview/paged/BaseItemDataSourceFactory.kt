package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseItemDataSourceFactory<
        ITEM : DiffCallback.DiffItemUniqueId<TYPE_ID>,
        TYPE_ID,
        API,
        DATA_SOURCE : BaseItemDataSource<ITEM, TYPE_ID, API>>(
        private val compositeDisposable: CompositeDisposable,
        private val api: API,
        private val itemDataSource: DATA_SOURCE)

    : DataSource.Factory<TYPE_ID, ITEM>() {

    private val TAG = "TAG_${BaseItemDataSourceFactory::class.java.simpleName}"

    val articlesDataSourceLiveData = MutableLiveData<DATA_SOURCE>()

    abstract override fun create(): DataSource<TYPE_ID, ITEM>?

}

