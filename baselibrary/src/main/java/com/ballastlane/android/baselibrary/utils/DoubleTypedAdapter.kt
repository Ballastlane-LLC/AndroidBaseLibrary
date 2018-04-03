package com.ballastlane.android.baselibrary.utils

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class DoubleTypedAdapter : JsonSerializer<Double> {

    override fun serialize(src: Double?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement? {
        return if (src == null || src == -1.0) null else JsonPrimitive(src)

    }
}