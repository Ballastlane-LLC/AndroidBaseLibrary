package com.ballastlane.android.baselibrary.common.base.recyclerview.paged

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
interface BaseRecyclerViewInterface<ITEM> {

     var itemList: LiveData<PagedList<ITEM>>
}