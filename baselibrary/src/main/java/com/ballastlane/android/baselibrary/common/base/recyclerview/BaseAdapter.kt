package com.ballastlane.android.baselibrary.common.base.recyclerview

import android.support.v7.widget.RecyclerView

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseAdapter<ITEM>(
        private val listener: OnItemSelected<ITEM>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "TAG_${BaseAdapter::class.java.simpleName}"

    private val itemList: MutableList<ITEM>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it -> listener.onItemSelected(it) }
        }
    }

    abstract fun refreshItems(itemList: MutableList<ITEM>?)

    private fun getItem(position: Int): ITEM? {
        itemList?.let { return it[position] }
        return null
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    interface OnItemSelected<ITEM> {
        fun onItemSelected(article: ITEM)
    }
}