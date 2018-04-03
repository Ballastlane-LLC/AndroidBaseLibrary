package com.ballastlane.ballastlanebaseapp.data.managers.remote

import com.ballastlane.ballastlanebaseapp.data.repositories.user.source.RestUser
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class RestManagerPrivateService : BaseService<ApiServiceInterface>() {

    fun createUser(user: RestUser): Single<RestUser> {
        return getApiService()
                .createUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateUser(user: RestUser): Single<RestUser> {
        return getApiService()
                .updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
