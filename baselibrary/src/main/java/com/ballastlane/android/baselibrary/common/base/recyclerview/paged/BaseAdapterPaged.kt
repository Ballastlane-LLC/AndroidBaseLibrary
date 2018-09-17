package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import com.ballastlane.android.baselibrary.common.base.recyclerview.BaseAdapter

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseAdapterPaged<ITEM : DiffCallback.DiffItemUniqueId<TYPE_ID>, TYPE_ID>(
        private val listener: OnItemSelected<ITEM>,
        diffCallback: DiffCallback<ITEM, TYPE_ID>
) : PagedListAdapter<ITEM, RecyclerView.ViewHolder>(diffCallback) {

    private val TAG = "TAG_${BaseAdapter::class.java.simpleName}"

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it -> listener.onItemSelected(it) }
        }
    }

    interface OnItemSelected<ITEM> {
        fun onItemSelected(article: ITEM)
    }

}