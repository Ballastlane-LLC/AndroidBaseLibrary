package com.ballastlane.android.baselibrary.model

import android.os.Parcel
import android.os.Parcelable
import com.ballastlane.android.baselibrary.common.ext.createParcel

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
data class Session(
        val id: String? = null,
        val createdAt: String? = null
): Parcelable {

    companion object {
        @JvmField
        val CREATOR = createParcel {
            val id = it.readString()
            val createdAt = it.readString()

            Session(
                    id = id,
                    createdAt = createdAt)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(createdAt)
    }
}