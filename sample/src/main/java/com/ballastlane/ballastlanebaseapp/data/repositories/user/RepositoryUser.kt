package com.ballastlane.ballastlanebaseapp.data.repositories.user

import com.ballastlane.ballastlanebaseapp.data.repositories.user.source.RestUserSource
import com.ballastlane.ballastlanebaseapp.entities.User

import io.reactivex.Single

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/3/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class RepositoryUser : RepositoryUserDataSource {

    private val restSource: RestUserSource
        get() {
            if (sourceRest == null) {
                sourceRest = RestUserSource.newInstance()
            }
            return sourceRest as RestUserSource
        }

    override fun get(userId: String): Single<User> {
        return restSource.get(userId)
    }

    override fun create(id: String, name: String): Single<User> {
        return restSource.create(id, name)
    }

    override fun update(id: String, name: String): Single<User> {
        return restSource.update(id, name)
    }

    companion object {

        private var instance: RepositoryUser? = null
        private var sourceRest: RestUserSource? = null

        fun newInstance(): RepositoryUser {
            if (instance == null) {
                instance = RepositoryUser()
            }
            return instance as RepositoryUser
        }
    }

}
