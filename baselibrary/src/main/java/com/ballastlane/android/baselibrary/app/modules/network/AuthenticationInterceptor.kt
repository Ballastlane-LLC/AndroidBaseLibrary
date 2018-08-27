package com.ballastlane.android.baselibrary.app.modules.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        return null
    }
}