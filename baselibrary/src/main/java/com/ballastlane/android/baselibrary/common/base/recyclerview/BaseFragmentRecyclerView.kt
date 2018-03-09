package com.ballastlane.android.baselibrary.common.base.recyclerview

import android.os.Bundle
import android.os.Handler
import android.support.annotation.CallSuper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.View
import com.ballastlane.android.baselibrary.common.base.BaseFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseFragmentRecyclerView<M> : BaseFragment() {

    private val TAG = "TAG_${BaseFragmentRecyclerView::class.java.simpleName}"

    var isHeaderEnabled: Boolean = false
        protected set(headerEnabled) {
            field = headerEnabled
            if (adapter != null) {
                adapter!!.isHeaderEnabled = headerEnabled
            }
        }
    var isLoadEnabled = true
        protected set(loadEnabled) {
            field = loadEnabled
            if (adapter != null) {
                adapter!!.isLoadEnabled = loadEnabled
            }
        }
    var isLoadMoreEnabled: Boolean = false
        protected set(loadMoreEnabled) {
            field = loadMoreEnabled
            if (adapter != null) {
                adapter!!.isLoadMoreEnabled = loadMoreEnabled
            }
        }
    var isRefreshEnabled: Boolean = false
        protected set(refreshEnabled) {
            field = refreshEnabled
            if (adapter != null) {
                adapter!!.isRefreshEnabled = refreshEnabled
            }
        }
    var adapter: BaseAdapter<M, BaseAdapter.BaseViewHolder<M>>? = null

    protected abstract val recyclerView: RecyclerView?

    private val actionOnLoadError: Consumer<Throwable>
        get() = Consumer { doCancelLoading() }

    private val funcActionItemsLoaded: (Pair<List<M>, Boolean>) -> Single<Pair<List<M>, Boolean>>
        get() = { pair ->
            itemsLoaded(pair.first as MutableList<M>, pair.second)
            Single.just(pair)
        }

    /**
     * get pair that contains the items from a source and a boolean
     * that indicates if it has more items pending to load for the first batch of loading
     */
    protected abstract val itemsFromSource: Single<Pair<List<M>, Boolean>>

    private val funcActionMoreItemsLoaded: Function<Pair<List<M>, Boolean>, Single<Pair<List<M>, Boolean>>>
        get() = Function { pair ->
            moreItemsLoaded(pair.first, pair.second)
            Single.just(pair)
        }

    /**
     * get pair that contains the items from a source and a boolean
     * that indicates if it has more items pending to load for the first batch of loading
     */
    protected abstract val moreItemsFromSource: Single<Pair<List<M>, Boolean>>

    private val funcActionRefreshItems: Function<Pair<List<M>, Boolean>, Single<Pair<List<M>, Boolean>>>
        get() = Function { pair ->
            refreshItemsLoaded(pair.first as MutableList<M>, pair.second)
            Single.just(pair)
        }

    val isLoadingElements: Boolean
        get() = adapter!!.isLoading || adapter!!.isLoadingMore || adapter!!.isRefreshing

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter != null) {
            adapter!!.isHeaderEnabled = isHeaderEnabled
            adapter!!.isLoadEnabled = isLoadEnabled
            adapter!!.isLoadMoreEnabled = isLoadMoreEnabled
            adapter!!.isRefreshEnabled = isRefreshEnabled
        } else {
            throw RuntimeException(String.format("Adapter of the RecyclerView %s MUST be initialized on the initVars() method by the setAdapter()", this.javaClass.simpleName))
        }

        if (recyclerView == null) {
            throw RuntimeException(String.format("RecyclerView of the view %s MUST be setter on the initViews() method by the setRecyclerView()", this.javaClass.simpleName))
        }
        recyclerView!!.adapter = adapter
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = adapter!!.itemCount
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                if (adapter!!.isLoadMoreEnabled && !isLoadingElements && lastVisibleItemPosition == totalItemCount - 1) {
                    val handler = Handler()
                    val r = Runnable { doLoadMoreItems() }
                    handler.post(r)
                }

            }
        })
    }

    /**
     * Make the fragment trigger the first load of data on the adapter
     */
    @CallSuper
    protected fun doLoadItems() {
        adapter!!.isLoading = true
        adapter!!.clearItems()
        adapter!!.notifyDataSetChanged()
        adapter!!.checkLoadingViewState()
        itemsFromSource
                .doOnError(actionOnLoadError)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(funcActionItemsLoaded)
                .subscribe(onLoadItemsSuccess(), onLoadItemsFail())
    }

    private fun itemsLoaded(items: MutableList<M>, isThereMoreDataToLoad: Boolean) {
        adapter!!.isLoadMoreEnabled = isThereMoreDataToLoad
        adapter!!.isLoading = false
        adapter!!.checkLoadingViewState()
        adapter!!.refreshItems(items)
    }

    protected fun onLoadItemsSuccess(): Consumer<in Pair<List<M>, Boolean>>? {
        return Consumer { }
    }

    protected fun onLoadItemsFail(): Consumer<Throwable> {
        return Consumer { }
    }

    /**
     * Make the fragment trigger load more data on the adapter
     */
    private fun doLoadMoreItems() {
        adapter!!.isLoadingMore = true
        adapter!!.checkLoadingMoreViewState()
        recyclerView!!.smoothScrollToPosition(adapter!!.itemCount - 1)
        moreItemsFromSource
                .doOnError(actionOnLoadError)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(funcActionMoreItemsLoaded)
                .subscribe(onLoadMoreItemsSuccess(), onLoadMoreItemsFail())
    }

    private fun moreItemsLoaded(moreItems: List<M>, isThereMoreDataToLoad: Boolean) {
        adapter!!.isLoadMoreEnabled = isThereMoreDataToLoad
        adapter!!.isLoadingMore = false
        adapter!!.checkLoadingMoreViewState()
        adapter!!.addItems(moreItems)
    }

    protected fun onLoadMoreItemsSuccess(): Consumer<Pair<List<M>, Boolean>> {
        return Consumer { }
    }

    protected fun onLoadMoreItemsFail(): Consumer<Throwable> {
        return Consumer { }
    }

    /**
     * Make the fragment trigger refresh data on the adapter
     */
    @CallSuper
    protected fun doRefreshItems() {
        adapter!!.isLoadMoreEnabled = false
        adapter!!.isRefreshing = true
        itemsFromSource
                .doOnError(actionOnLoadError)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(funcActionRefreshItems)
                .subscribe(onRefreshItemsLoadSuccess(), onRefreshItemsLoadFail())
    }

    private fun refreshItemsLoaded(refreshItems: MutableList<M>, isThereMoreDataToLoad: Boolean) {
        adapter!!.isLoadMoreEnabled = isThereMoreDataToLoad
        adapter!!.isRefreshing = false
        adapter!!.refreshItems(refreshItems)
    }

    protected fun onRefreshItemsLoadSuccess(): Consumer<Pair<List<M>, Boolean>> {
        return Consumer { }
    }

    protected fun onRefreshItemsLoadFail(): Consumer<Throwable> {
        return Consumer { }
    }

    @CallSuper
    protected fun doCancelLoading() {
        adapter!!.isLoading = false
        adapter!!.isLoadingMore = false
        adapter!!.isRefreshing = false
        adapter!!.notifyDataSetChanged()
    }

}



