package com.ballastlane.android.baselibrary.common.base.recyclerview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import java.util.*

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseSwipableAdapter<T, H : BaseAdapter.BaseViewHolder<*>> : BaseAdapter<T, H>() {

    private val TAG = "TAG_${BaseSwipableAdapter::class.java.simpleName}"

    private var whereItemStartToMove: Int = 0
    private var whereItemEndToMove: Int = 0
    var isCallbackMoved: Boolean = false
    var isCallbackSwiped: Boolean = false
    var itemTouchHelperAdapter: ItemTouchHelperAdapter<T>? = null
        private set

    private fun changeItemsByPosition(adapterPositionStart: Int, adapterPositionEnd: Int): Boolean {
        if (isHeaderEnabled && adapterPositionEnd == 0) {
            return false
        }
        if (adapterPositionEnd > items.size) {
            return false
        }
        if (adapterPositionStart < adapterPositionEnd) {
            for (i in getItemPosition(adapterPositionStart) until getItemPosition(adapterPositionEnd)) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in getItemPosition(adapterPositionStart) downTo getItemPosition(adapterPositionEnd) + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        whereItemEndToMove = adapterPositionEnd
        notifyItemMoved(adapterPositionStart, adapterPositionEnd)
        if (itemTouchHelperAdapter != null) {
            val itemFrom = items[getItemPosition(adapterPositionStart)]
            val itemTarget = items[getItemPosition(adapterPositionEnd)]
            itemTouchHelperAdapter!!.onItemMoved(adapterPositionStart,
                    getItemPosition(adapterPositionStart),
                    itemFrom,
                    getItemPosition(adapterPositionEnd),
                    adapterPositionEnd,
                    itemTarget)
        }
        return true
    }

    /**
     * Insert an item in list by position.
     *
     * @param position       is the position adapter of the items.
     * @param itemsToDismiss is the item that you previews remove
     */
    fun undoRemovedItem(position: Int, itemsToDismiss: T) {
        addItemAtPosition(itemsToDismiss, position)
    }

    fun undoLastItemsChangesPosition(): Boolean {
        return changeItemsByPosition(whereItemEndToMove, whereItemStartToMove)
    }

    fun addItemTouchHelperAdapter(recyclerView: RecyclerView, itemTouchHelperAdapter: ItemTouchHelperAdapter<T>, activeSwipe: Boolean, activeMove: Boolean) {
        this.itemTouchHelperAdapter = itemTouchHelperAdapter
        isCallbackMoved = activeMove
        isCallbackSwiped = activeSwipe
        val callback = SimpleItemTouchHelperCallback()
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    interface ItemTouchHelperAdapter<T> {

        fun onItemMoved(fromAdapterPosition: Int, fromItemPosition: Int, itemOrigin: T, toAdapterPosition: Int, toItemsPosition: Int, itemTarget: T)

        fun onItemDismissed(position: Int, item: T)
    }

    /**
     * Move Items
     */
    inner class SimpleItemTouchHelperCallback : ItemTouchHelper.Callback() {

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            var dragFlags = 0
            if (isCallbackMoved) {
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            }
            var swipeFlags = 0
            if (isCallbackSwiped) {
                swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            }
            if (isHeaderEnabled && viewHolder.adapterPosition == 0 || items.size == 0) {
                return 0
            }
            whereItemStartToMove = viewHolder.adapterPosition
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean {
            return changeItemsByPosition(viewHolder.adapterPosition,
                    target.adapterPosition)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (!(isHeaderEnabled && viewHolder.adapterPosition == 0)) {
                val position = getItemPosition(viewHolder.adapterPosition)
                val item = items[position]
                items.removeAt(position)
                notifyItemRemoved(position + headerViewCount())
                if (itemTouchHelperAdapter != null) {
                    itemTouchHelperAdapter!!.onItemDismissed(position,
                            item)
                }
            }
        }
    }

}
