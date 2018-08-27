package com.ballastlane.android.baselibrary.utils

import com.google.gson.Gson

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class StringHelper {

    private val TAG = "TAG_${StringHelper::class.java.simpleName}"

    private val INDEX_NOT_FOUND = -1

    fun toString(o: Any?): String {
        return Gson().toJson(o)
    }
}