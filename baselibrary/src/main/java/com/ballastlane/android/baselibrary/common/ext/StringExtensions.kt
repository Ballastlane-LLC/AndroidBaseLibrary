package com.ballastlane.android.baselibrary.common.ext

import android.content.Context
import android.content.Intent
import java.util.regex.Pattern

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

fun String?.empty() = this == null || this.trim().isEmpty()

fun String.isEmail(): Boolean {
    val regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String?.ifEmptyThenReturn(blankDefault: CharSequence): String {
    return if (this == null || this.toString().trim { it <= ' ' }.isEmpty() ||
            this.toString().equals("null", ignoreCase = true)) blankDefault.toString()
    else this.toString()
}

fun String?.ifNotEmptyThenPrefix(prefix: CharSequence, blankDefault: CharSequence): String {
    return if (this == null || this.toString().trim { it <= ' ' }.isEmpty() ||
            this.toString().equals("null", ignoreCase = true)) blankDefault.toString()
    else prefix.toString() + this.toString()
}

fun String?.ifNotEmptyThenSuffix(suffix: CharSequence, blankDefault: CharSequence): String {
    return if (this == null || this.toString().trim { it <= ' ' }.isEmpty() ||
            this.toString().equals("null", ignoreCase = true)) blankDefault.toString()
    else this.toString() + suffix.toString()
}

fun String?.ifNotEmptyThenPrefixAndSuffix(prefix: CharSequence, suffix: CharSequence, blankDefault: CharSequence): String {
    return if (this == null || this.toString().trim { it <= ' ' }.isEmpty() ||
            this.toString().equals("null", ignoreCase = true)) blankDefault.toString()
    else prefix.toString() + (this.toString() + suffix.toString())
}

fun String?.share(ctx: Context, title: String) {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "text/plain"
    sharingIntent.putExtra(Intent.EXTRA_TEXT, this)
    ctx.startActivity(Intent.createChooser(sharingIntent, title))
}

