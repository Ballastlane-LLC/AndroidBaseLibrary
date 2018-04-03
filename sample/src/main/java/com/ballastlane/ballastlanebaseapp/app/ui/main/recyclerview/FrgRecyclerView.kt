package com.ballastlane.ballastlanebaseapp.app.ui.main.recyclerview

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.recyclerview.BaseFragmentRecyclerView
import com.ballastlane.ballastlanebaseapp.entities.Item
import com.kogimobile.baselibrary.sample.R
import io.reactivex.Single
import io.reactivex.functions.Consumer

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class FrgRecyclerView : BaseFragmentRecyclerView<Item>() {
    override val recyclerView: RecyclerView?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val itemsFromSource: Single<Pair<List<Item>, Boolean>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val moreItemsFromSource: Single<Pair<List<Item>, Boolean>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun initVars() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //    private var binding: FrgRecyclerviewBinding? = null
    private var viewModel: ViewModelRecyclerView? = null

//    protected override val recyclerView: RecyclerView
//        get() = this.binding!!.recyclerView
//
//    protected override val itemsFromSource: Single<Pair<MutableList<Item>, Boolean>>?
//        get() = this.viewModel!!.listItems
//
//    protected override val moreItemsFromSource: Single<Pair<MutableList<Item>, Boolean>>?
//        get() = this.viewModel!!.moreListItems
//
//    protected override fun initVars() {
//        setLoadMoreEnabled(true)
//        setAdapter(AdapterItems())
//    }

//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.viewModel = ViewModelProviders.of(this).get(ViewModelRecyclerView::class.java)
//        doLoadItems()
//    }
//
//    fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {
//        this.binding = DataBindingUtil.inflate<FrgRecyclerviewBinding>(inflater, R.layout.frg_recyclerview, container, false)
//        return this.binding!!.getRoot()
//    }
//
//    protected override fun initViews() {
//        recyclerView.layoutManager = LinearLayoutManager(getActivity())
//        recyclerView.setHasFixedSize(true)
//    }
//
//    protected override fun initListeners() {
//        this.binding!!.swipeRefresh.setOnRefreshListener(
//                SwipeRefreshLayout.OnRefreshListener { doRefreshItems() }
//        )
//    }
//
//    protected override fun onRefreshItemsLoadSuccess(): Consumer<Pair<List<Item>, Boolean>> {
//        return Consumer<Pair<List<Item>, Boolean>> { hideRefresh() }
//    }
//
//    protected override fun onRefreshItemsLoadFail(): Consumer<Throwable> {
//        return Consumer { hideRefresh() }
//    }
//
//    private fun hideRefresh() {
//        this.binding!!.swipeRefresh.setRefreshing(false)
//    }

    companion object {

        fun newInstance(): FrgRecyclerView {
            val fragment = FrgRecyclerView()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}