package com.ballastlane.android.baselibrary.utils

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 8/27/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class DateTimeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    override fun serialize(src: DateTime,
                           typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement {

        val fmt = ISODateTimeFormat.dateTimeParser()
        return JsonPrimitive(fmt.print(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): DateTime? {

        if (json.asString == null || json.asString.isEmpty()) {
            return null
        }

        val fmt = ISODateTimeFormat.dateTimeParser()
        return fmt.parseDateTime(json.asString)
    }

    companion object {
        private val TAG = DateTimeConverter::class.java.simpleName
    }
}