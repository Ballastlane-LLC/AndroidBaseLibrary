package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ballastlane.android.baselibrary.common.base.BaseFragment

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseFragmentRecyclerView<M> : BaseFragment() {

    private val TAG = "TAG_${BaseFragmentRecyclerView::class.java.simpleName}"

    var adapter: BaseAdapter<M, BaseAdapter.BaseViewHolder<M>>? = null

    protected abstract val recyclerView: RecyclerView?

    /**
     * get pair that contains the items from a source and a boolean
     * that indicates if it has more items pending to load for the first batch of loading
     */
    protected abstract val itemsFromSource: LiveData<PagedList<M>>

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (recyclerView == null) {
            throw RuntimeException(String.format("RecyclerView of the view %s MUST be setter on the initViews() method by the setRecyclerView()", this.javaClass.simpleName))
        }
        recyclerView!!.adapter = adapter
    }

    /**
     * Make the fragment trigger the first load of data on the adapter
     */
    @CallSuper
    protected open fun initRequestItems() {
        itemsFromSource.observe(this, Observer<PagedList<M>> {
            adapter!!.refreshItems(it)
        })
    }


    /**
     * Make the fragment trigger refresh data on the adapter
     */
    @CallSuper
    protected open fun refreshItems() {
        initRequestItems()
    }
}



