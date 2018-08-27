package com.ballastlane.android.baselibrary.common.base.recyclerview.paged;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Pedro Scott. pedro@kogimobile.com
 * @version 9/4/16. *
 * Copyright 2015 Kogi Mobile
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @modified Julian Cardona. julian@kogimobile.com
 * @modified by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

public abstract class BaseAdapter<T, H extends BaseAdapter.BaseViewHolder> extends PagedListAdapter<T, H> {

    private onItemClickListener<T> clickListener;

    protected BaseAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public PagedList<T> getItems() {
        return this.getCurrentList();
    }

    @Override
    public int getItemCount() {
       return getSize();
    }

    public void callItemListenerAtPosition(int adapterPosition) {
        if (getClickListener() != null) {
            getClickListener().onItemViewsClick(getItems().get(adapterPosition), adapterPosition);
        }
    }

    @CallSuper
    @Override
    public void onBindViewHolder(H holder, int position) {
        if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).bindView();
        } else {
            holder.bindView(getItems().get(position));
        }
    }

    public void refreshItems(@Nullable PagedList<T> list) {
        submitList(list);
    }

    private int getSize() {
        return getCurrentList() == null ? 0 : getCurrentList().size();
    }

    public onItemClickListener<T> getClickListener() {
        return this.clickListener;
    }

    public void addClickListener(onItemClickListener<T> clickListener) {
        this.clickListener = clickListener;
    }

    public interface onItemClickListener<T> {
        void onItemViewsClick(T item, int position);
    }

    public static class EmptyViewHolder<T> extends BaseViewHolder<T> {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @CallSuper
        @Override
        public void bindView(T item) {

        }

        @CallSuper
        protected void bindView() {

        }
    }

    public static abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        private Context context;
        private ViewDataBinding binding;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.binding = DataBindingUtil.bind(itemView);
        }

        @CallSuper
        protected <B extends ViewDataBinding> B getBinding() {
            return (B) binding;
        }

        protected abstract void bindView(T item);

        @CallSuper
        public Context getContext() {
            return this.context;
        }

    }


}