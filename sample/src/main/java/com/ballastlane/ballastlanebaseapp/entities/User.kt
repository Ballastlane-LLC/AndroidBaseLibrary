package com.ballastlane.ballastlanebaseapp.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/3/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class User : Parcelable {

    private var id: String? = null
    private var name: String? = null

    private constructor(builder: Builder) {
        this.id = builder.id
        this.name = builder.name
    }

    class Builder {

        var id: String? = null
        var name: String? = null

        fun withId(id: String?): Builder {
            this.id = id
            return this
        }

        fun withName(name: String?): Builder {
            this.name = name
            return this
        }

        fun build(): User {
            return User(this)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.name)
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.name = `in`.readString()
    }

    companion object {

        val builder: Builder
            get() = Builder()

        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User {
                return User(source)
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOfNulls(size)
            }
        }
    }
}
