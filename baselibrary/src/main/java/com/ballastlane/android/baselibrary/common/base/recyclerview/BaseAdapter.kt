package com.ballastlane.android.baselibrary.common.base.recyclerview

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.ArrayList

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseAdapter<T, H : BaseAdapter.BaseViewHolder<*>> : RecyclerView.Adapter<H>() {

    private val TAG = "TAG_${BaseAdapter::class.java.simpleName}"

    var isLoading = true
    var isLoadingMore: Boolean = false
    var isRefreshing: Boolean = false
    var isHeaderEnabled: Boolean = false
    var isLoadEnabled = true
    var isLoadMoreEnabled: Boolean = false
    var isRefreshEnabled: Boolean = false
    var clickListener: BaseAdapter.onItemClickListener<T>? = null
        private set

    private val headerAndLoadingCount: Int
        get() = headerViewCount() + loadViewCount()

    private val isItemsEmpty: Boolean
        get() = items.isEmpty()

    var items: MutableList<T> = ArrayList()

    override fun getItemCount(): Int {
        validateItemsNullAndCreate()
        return if (isItemsEmpty) {
            validateNumberOfItemsFormEmptyList()
        } else {
            headerViewCount() + items.size + loadMoreViewCount()
        }
    }

    protected fun validateNumberOfItemsFormEmptyList(): Int {
        return if (headerAndLoadingCount == 0) EMPTY_VIEW_NUMBER_OF_ITEMS else headerAndLoadingCount
    }

    override fun getItemViewType(position: Int): Int {
        validateItemsNullAndCreate()
        return if (position == 0) {
            if (isHeaderEnabled) {
                HEADER_VIEW
            } else if (isLoadEnabled && isLoading && isItemsEmpty) {
                LOADING_VIEW
            } else if (items.size == 0) {
                EMPTY_VIEW
            } else {
                super.getItemViewType(position)
            }
        } else if (position == 1) {
            if (isLoadEnabled && isLoading && isItemsEmpty) {
                LOADING_VIEW
            } else if (items.size == 0) {
                EMPTY_VIEW
            } else if (isLoadMoreEnabled && isLoadingMore && position == headerViewCount() + items.size - 1 + loadMoreViewCount()) {
                LOAD_MORE_VIEW
            } else {
                super.getItemViewType(position)
            }
        } else {
            if (isLoadMoreEnabled && isLoadingMore && position == headerViewCount() + items.size - 1 + loadMoreViewCount()) {
                LOAD_MORE_VIEW
            } else {
                super.getItemViewType(position)
            }
        }
    }

    protected fun validateItemsNullAndCreate() {
        // TODO :: FIND ANOTHER CASE BUT I THINK THAT WE DON'T NEED THIS
    }

    /**
     * This method find an item adapter by position.
     *
     * @param position Where the item is in the list of base items.
     * @return The item that found in the position or null in case to don't founded.
     */
    fun getItem(position: Int): T? {
        return if (position - headerViewCount() < items.size) {
            items.elementAt(position - headerViewCount())
        } else null
    }

    /**
     * This method add the new list of items if you past a null.
     * the list of items go to create a new entry list.
     *
     * @param list list of items to add in the base list of items in the adapter.
     */
    fun addItems(list: List<T>) {
        validateItemsNullAndCreate()
        if (list.isNotEmpty()) {
            val startRange = itemCount + 1
            items.addAll(list)
            notifyItemRangeInserted(startRange, list.size)
        }
    }

    /**
     * This method clear the base list items and add the new list of items.
     * if you past a null the list of items go to create a new entry list.
     *
     * @param list list of items to insert in the base list of items in the adapter.
     */
    fun refreshItems(list: MutableList<T>) {
        validateItemsNullAndCreate()
        clearItems()
        items.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * This method find and remove an item adapter by position.
     *
     * @param item to remove in list.
     * @return The true in case to find and remove the items.
     */
    fun removeItem(item: T): Boolean {
        validateItemsNullAndCreate()
        val index = items.indexOf(item)
        if (index != -1) {
            items.remove(item)
            notifyItemRemoved(index)
            return true
        }
        return false
    }

    /**
     * This method add an item adapter and notify all the adapter.
     *
     * @param item the item to insert in list.
     */
    fun addItem(item: T) {
        validateItemsNullAndCreate()
        items.add(item)
        notifyItemInserted(getItemPosition(itemCount))
    }

    /**
     * This method add an item adapter by position
     *
     * @param item     the item to insert in list.
     * @param position the position to insert in list
     */
    fun addItemAtPosition(item: T, position: Int) {
        validateItemsNullAndCreate()
        if (position < items.size) {
            items.add(position, item)
            notifyItemInserted(position + headerViewCount())
        }
    }

    /**
     * This method add an item adapter and notify all the adapter.
     *
     * @param item the item to insert in list.
     */
    fun updateItemAtPosition(position: Int, item: T) {
        validateItemsNullAndCreate()
        items[position] = item
        notifyItemChanged(position)
    }

    /**
     * This method find and remove an item adapter by position
     *
     * @param position Where the item is in the list of base items.
     * @return The true in case to find and remove the items.
     */
    fun removeItemAtPosition(position: Int): Boolean {
        validateItemsNullAndCreate()
        if (position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position + headerViewCount())
            return true
        }
        return false
    }

    /**
     * Call the click item  Listener with the position of the item in the adapter and call
     * the @onItemViewsClick() executing the position rules and giving to the listener the position
     * in the list items and the current item.
     *
     * @param adapterPosition Receive the position of the adapter.
     */
    fun callItemListenerAtPosition(adapterPosition: Int) {
        if (clickListener != null) {
            clickListener!!.onItemViewsClick(items[getItemPosition(adapterPosition)], getItemPosition(adapterPosition))
        }
    }

    fun headerViewCount(): Int {
        return if (isHeaderEnabled) 1 else 0
    }

    fun loadViewCount(): Int {
        return if (isLoadEnabled && isLoading && isItemsEmpty) 1 else 0
    }

    fun loadMoreViewCount(): Int {
        return if (isLoadMoreEnabled && isLoadingMore) 1 else 0
    }

    /**
     * Get position of the item in the list.
     *
     * @param adapterPosition Receive the position of the adapter.
     * @return The Position of the items in the list.
     */
    fun getItemPosition(adapterPosition: Int): Int {
        return adapterPosition - headerViewCount()
    }

    fun checkLoadingViewState() {
        val loadingViewPosition = headerViewCount()
        if (isLoading) {
            notifyItemInserted(itemCount)
        } else {
            notifyItemRemoved(loadingViewPosition)
        }
    }

    fun checkLoadingMoreViewState() {
        val loadingMoreViewPosition = headerViewCount() + items.size
        if (isLoadingMore) {
            notifyItemInserted(itemCount)
        } else {
            notifyItemRemoved(loadingMoreViewPosition)
        }
    }

    fun clearItems() {
        validateItemsNullAndCreate()
        items.clear()
    }

    fun addClickListener(clickListener: BaseAdapter.onItemClickListener<T>) {
        this.clickListener = clickListener
    }

    interface onItemClickListener<T> {
        fun onItemViewsClick(item: T, position: Int)
    }

    @CallSuper
    override fun onBindViewHolder(holder: H, position: Int) {
        if (holder is EmptyViewHolder<*>) {
            (holder as EmptyViewHolder<*>).bindView()
        } else {
            val pos = getItemPosition(position)
            holder.bindView(items[pos])
        }
    }

    class EmptyViewHolder<T>(itemView: View) : BaseAdapter.BaseViewHolder<T>(itemView) {

        @CallSuper
        override fun bindView(item: Any?) {

        }

        @CallSuper
        fun bindView() {

        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @get:CallSuper
        val context: Context = itemView.context
        private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)

        @CallSuper
        protected fun <B : ViewDataBinding> getBinding(): B {
            return binding as B
        }

        abstract fun bindView(item: Any?)
    }

    companion object {

        protected val EMPTY_VIEW = 2000
        protected val HEADER_VIEW = 3000
        protected val LOADING_VIEW = 4000
        protected val LOAD_MORE_VIEW = 5000
        private val EMPTY_VIEW_NUMBER_OF_ITEMS = 1
    }
}