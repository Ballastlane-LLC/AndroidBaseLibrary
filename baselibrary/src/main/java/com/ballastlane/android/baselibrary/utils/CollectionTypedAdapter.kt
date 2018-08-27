package com.ballastlane.android.baselibrary.utils

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

open class CollectionTypedAdapter : JsonSerializer<Collection<*>> {

    override fun serialize(
            src: Collection<*>?,
            typeOfSrc: Type,
            context: JsonSerializationContext): JsonElement? {

        if (src == null)
            return null

        val array = JsonArray()

        for (child in src) {
            val element = context.serialize(child)
            array.add(element)
        }

        return array
    }
}
