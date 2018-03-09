package com.ballastlane.android.baselibrary.common.ext

import android.content.Context
import java.io.*
import java.net.URLConnection

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

private val TAG = "TAG_File"

@Throws(IOException::class)
fun String.saveObjectToDiskAsFile(context: Context, `object`: Any) {
    val fileOutputStream = context.openFileOutput(this, Context.MODE_PRIVATE)
    val objectOutputStream = ObjectOutputStream(fileOutputStream)
    objectOutputStream.writeObject(`object`)
    objectOutputStream.close()
    fileOutputStream.close()
}

@Throws(IOException::class, ClassNotFoundException::class)
fun String.getObjectFromDiskAsFile(context: Context): Any? {
    var fis: FileInputStream? = null
    var `object`: Any? = null
    fis = context.openFileInput(this)
    val `is` = ObjectInputStream(fis)
    `object` = `is`.readObject()
    `is`.close()
    return `object`
}

@Throws(IOException::class)
fun String.deleteObjectOnDiskAsFile(context: Context) {
    val fileOutputStream = context.openFileOutput(this, Context.MODE_PRIVATE)
    val objectOutputStream = ObjectOutputStream(fileOutputStream)
    objectOutputStream.reset()
    objectOutputStream.close()
    fileOutputStream.close()
}

fun String.createDirAsFile(): Boolean {
    var isDirCreated = true
    val f = File(this)
    if (!f.exists()) {
        isDirCreated = f.mkdirs()
    }
    return isDirCreated
}

fun String.checkExistAsFile(): Boolean {
    val temp = File(this)
    return temp.exists()
}

fun String.getMimeTypeAsFile(): String {
    return URLConnection.guessContentTypeFromName(this)
}