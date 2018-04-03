package com.ballastlane.ballastlanebaseapp.app.ui.main.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.recyclerview.BaseAdapter
import com.ballastlane.ballastlanebaseapp.entities.Item
import com.kogimobile.baselibrary.sample.R

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */


class AdapterItems : BaseAdapter<Item, BaseAdapter.BaseViewHolder<Item>>() {

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: ViewDataBinding
        when (viewType) {
            EMPTY_VIEW -> {
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_empty, parent, false)
                return EmptyViewHolder(binding.root)
            }
            LOAD_MORE_VIEW -> {
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_loading_more, parent, false)
                return EmptyViewHolder(binding.root)
            }
            LOADING_VIEW -> {
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_loading, parent, false)
                return EmptyViewHolder(binding.root)
            }
            else -> {
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)
                return ThemeHolder(binding.root)
            }
        }
    }

    internal inner class ThemeHolder(itemView: View) : BaseViewHolder<Item>(itemView) {

        protected val binding: ListItemBinding
            get() = super.getBinding()

        protected fun bindView(item: Item) {
            binding.itemName.setText(context.getString(R.string.frg_recycler_view_item_name, getAdapterPosition()))
        }

    }

}