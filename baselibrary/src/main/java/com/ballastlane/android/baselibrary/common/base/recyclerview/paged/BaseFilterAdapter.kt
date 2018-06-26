package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.paging.PagedList
import android.support.v7.util.DiffUtil
import android.widget.Filter
import android.widget.Filterable
import java.util.*

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseFilterAdapter<T, H : BaseAdapter.BaseViewHolder<*>>(diffCallback: DiffUtil.ItemCallback<T>) : BaseAdapter<T, H>(diffCallback), Filterable {

    private val TAG = "TAG_${BaseFilterAdapter::class.java.simpleName}"

    private var minNumberToCleanFilter = 0

    private var filter: AdapterFilter? = null

    val itemsByCondition: List<T>
        get() {
            val selectedItems = ArrayList<T>()
            for (t in items) {
                if (ifValidCondition(t))
                    selectedItems.add(t)
            }
            return selectedItems
        }

    fun setMinNumberToCleanFilter(minNumberToCleanFilter: Int) {
        this.minNumberToCleanFilter = minNumberToCleanFilter
    }

    fun filterItems(query: String) {
        getFilter().filter(query)
    }

    override fun getFilter(): Filter {
        if (filter == null)
            filter = AdapterFilter(items)
        return filter as AdapterFilter
    }

    fun ifValidCondition(t: T): Boolean {
        return true
    }

    protected abstract fun searchCondition(item: T, query: String): Boolean

    internal inner class AdapterFilter(items: List<T>) : Filter() {

        private val originalList: MutableList<T>
        private val filteredItemList: MutableList<T>

        init {

            this.originalList = LinkedList(items)
            this.filteredItemList = ArrayList()
        }

        private fun getFilteredItemList(): MutableList<T> {
            return filteredItemList
        }

        override fun performFiltering(constraint: CharSequence): Filter.FilterResults {
            getFilteredItemList().clear()
            val results = Filter.FilterResults()
            if (constraint.length == minNumberToCleanFilter) {
                getFilteredItemList().addAll(originalList)
            } else {
                for (item in originalList) {
                    if (searchCondition(item, constraint.toString())) {
                        getFilteredItemList().add(item)
                    }
                }
            }
            results.values = getFilteredItemList()
            results.count = getFilteredItemList().size
            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            refreshItems(results.values as PagedList<T>)
            notifyDataSetChanged()
        }
    }

}
