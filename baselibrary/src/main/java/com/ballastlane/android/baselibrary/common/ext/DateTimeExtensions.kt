package com.ballastlane.android.baselibrary.common.ext

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 11/28/17.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */


fun String.convertToPatternFromPattern(fromPattern: String, toPattern: String): String {
    return this.getDateTimeFromPattern(fromPattern).getStringPatternFromDateTime(toPattern)
}

fun String.getDateTimeFromPattern(inputPattern: String): DateTime {
    var formatter: DateTimeFormatter = DateTimeFormat.forPattern(inputPattern)
    return formatter.parseDateTime(this)
}

fun DateTime.getStringPatternFromDateTime(pattern: String): String {
    var presenter: DateTimeFormatter = DateTimeFormat.forPattern(pattern)
    return presenter.print(this)
}