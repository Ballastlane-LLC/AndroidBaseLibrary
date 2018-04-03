package com.ballastlane.ballastlanebaseapp.data.repositories.user

import com.ballastlane.ballastlanebaseapp.entities.User

import io.reactivex.Single

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/3/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

interface RepositoryUserDataSource {

    operator fun get(userId: String): Single<User>

    fun create(id: String, name: String): Single<User>

    fun update(id: String, name: String): Single<User>

}
