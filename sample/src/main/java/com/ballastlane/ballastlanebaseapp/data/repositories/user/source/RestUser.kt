package com.ballastlane.ballastlanebaseapp.data.repositories.user.source

import com.ballastlane.ballastlanebaseapp.entities.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class RestUser private constructor(builder: Builder) {

    @Expose
    @SerializedName("id")
    val id: String?
    @Expose
    @SerializedName("name")
    val name: String?

    init {
        this.id = builder.id
        this.name = builder.name
    }

    fun toEntity(): User {
        return User.builder
                .withId(id)
                .withName(name)
                .build()
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

        fun build(): RestUser {
            return RestUser(this)
        }
    }

    companion object {

        val builder: Builder
            get() = Builder()
    }


}
