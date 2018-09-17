package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.support.v7.util.DiffUtil

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class DiffCallback<ITEM: DiffCallback.DiffItemUniqueId<TYPE_ID>, TYPE_ID>: DiffUtil.ItemCallback<ITEM>() {

    private val TAG = "TAG_${DiffCallback::class.java.simpleName}"

    override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
        return oldItem.unique == newItem.unique
    }

    override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean {
        return oldItem == newItem
    }

    interface DiffItemUniqueId<TYPE> {
        val unique: TYPE
    }
}



