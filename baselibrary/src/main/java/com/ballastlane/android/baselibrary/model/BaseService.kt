package com.ballastlane.android.baselibrary.model

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class BaseService<I> {

    var apiServiceInterface: I? = null

    /**
     * @param apiServiceInterface
     */
    @Deprecated("use @see com.kogimobile.androidstudiobaselib.rest.ApiServiceFactory\n" +
            "      ")
    constructor(apiServiceInterface: I) {
        this.apiServiceInterface = apiServiceInterface
    }

    fun getApiService(): I? {
        return apiServiceInterface
    }

}
