package com.ballastlane.ballastlanebaseapp.data.managers.remote

import com.ballastlane.ballastlanebaseapp.data.repositories.user.source.RestUser
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class RestManagerPublicService : BaseService<ApiServiceInterface>() {

    fun getUser(userId: String): Single<RestUser> {
        return getApiService()
                .getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}