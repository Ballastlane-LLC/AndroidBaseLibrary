package com.ballastlane.ballastlanebaseapp.data.repositories.user.source

import com.ballastlane.ballastlanebaseapp.data.managers.remote.RestClient
import com.ballastlane.ballastlanebaseapp.data.managers.remote.RestManagerPrivateService
import com.ballastlane.ballastlanebaseapp.data.managers.remote.RestManagerPublicService
import com.ballastlane.ballastlanebaseapp.data.repositories.user.RepositoryUserDataSource
import com.ballastlane.ballastlanebaseapp.entities.User
import io.reactivex.Single
import okhttp3.internal.Internal.instance

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/3/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

public class RestUserSource : RepositoryUserDataSource {

    companion object {
        var instance: RestUserSource? = null

        fun newInstance(): RestUserSource {
            if (instance == null) {
                instance = RestUserSource()
            }
            return instance as RestUserSource
        }

    }

    override fun get(userId: String): Single<User> {
        return (RestClient.instance.getRestClientManager().getService(RestManagerPublicService::class.java) as RestManagerPublicService)
                .getUser(userId)
                .map { restUser -> transformRestUserToEntity(restUser) }
    }

    override fun create(id: String, name: String): Single<User> {
        return (RestClient.instance.getRestClientManager().getService(RestManagerPrivateService::class.java) as RestManagerPrivateService)
                .createUser(
                        RestUser.Builder()
                                .withId(id)
                                .withName(name)
                                .build()
                )
                .map { restUser -> transformRestUserToEntity(restUser) }
    }

    override fun update(id: String, name: String): Single<User> {
        return (RestClient.instance.getRestClientManager().getService(RestManagerPrivateService::class.java) as RestManagerPrivateService)
                .updateUser(
                        RestUser.Builder()
                                .withId(id)
                                .withName(name)
                                .build()
                )
                .map { restUser -> transformRestUserToEntity(restUser) }
    }

    private fun transformRestUserToEntity(restUser: RestUser): User {
        return RestUser.Builder()
                .withId(restUser.id)
                .withName(restUser.name)
                .build()
                .toEntity()
    }
}
