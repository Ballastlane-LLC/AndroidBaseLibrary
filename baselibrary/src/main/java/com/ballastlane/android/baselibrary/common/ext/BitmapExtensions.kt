package com.ballastlane.android.baselibrary.common.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import timber.log.Timber

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

private val TAG = "TAG_Bitmap"


fun Bitmap.decodeSampledBitmapFromFile(filePath: String, requiredWidth: Int, requiredHeight: Int): Bitmap {
    val options = BitmapFactory.Options()
    val imageMaxFactor = 1.15f
    options.inJustDecodeBounds = true
    var sampleSize = 1
    BitmapFactory.decodeFile(filePath, options)
    val imageHeight = options.outHeight
    val imageWidth = options.outWidth

    if (imageHeight > requiredHeight || imageWidth > requiredWidth) {
        val halfHeight = imageHeight / 2
        val halfWidth = imageWidth / 2
        while (halfHeight / sampleSize > requiredHeight * imageMaxFactor || halfWidth / sampleSize > requiredWidth * imageMaxFactor) {
            sampleSize *= 2
        }
    }

    options.inJustDecodeBounds = false
    options.inSampleSize = sampleSize
    Timber.d(TAG, "Decoding bitmap on path: " + filePath + " with sample size of: " + sampleSize + " Dimensions:" + imageWidth + "x" + imageHeight)
    return BitmapFactory.decodeFile(filePath, options)
}


fun Bitmap.scaleBitmap(maxSize: Int): Bitmap {
    var bm = this
    var width = bm.width.toFloat()
    var height = bm.height.toFloat()

    if (width > height) {
        // landscape
        val ratio = width / maxSize
        Timber.d(TAG, "Width > Height ---------->  Width: $width Height: $height Max Size: $maxSize Radio : $ratio")
        width = maxSize.toFloat()
        height = height / ratio
    } else if (height > width) {
        // portrait
        val ratio = height / maxSize
        Timber.d(TAG, "Height > Height---------->  Width: $width Height: $height Max Size: $maxSize Radio : $ratio")
        height = maxSize.toFloat()
        width = width / ratio
    } else {
        // square
        height = maxSize.toFloat()
        width = maxSize.toFloat()
    }
    Timber.d(TAG, "after scaling Width and height are $width--$height")

    bm = Bitmap.createScaledBitmap(bm, width.toInt(), height.toInt(), true)
    return bm
}
