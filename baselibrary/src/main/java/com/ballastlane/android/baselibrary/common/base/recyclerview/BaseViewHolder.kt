package com.ballastlane.android.baselibrary.common.base.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseViewHolder<ITEM>(view: View, val layoutId: Int)  : RecyclerView.ViewHolder(view) {

    private val TAG = "TAG_${BaseViewHolder::class.java.simpleName}"

    abstract fun bindTo(article: ITEM?)

}