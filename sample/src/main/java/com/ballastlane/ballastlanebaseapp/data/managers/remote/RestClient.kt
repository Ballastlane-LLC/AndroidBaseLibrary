package com.ballastlane.ballastlanebaseapp.data.managers.remote

import com.kogimobile.baselibrary.sample.R
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http2.Header
import java.io.IOException

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class RestClient {

    companion object {
        private var instance: RestClient? = null
        var restClientManager: RestClientManager? = null
    }

    fun getInstance(): RestClient {
        if (instance == null) {
            instance = RestClient()
            val publicServiceConfiguration = ServiceConfiguration(
                    App.getGlobalContext().getString(R.string.base_url),
                    ApiServiceFactory(RestManagerPublicService::class.java),
                    ApiServiceInterface::class.java)

            val privateServiceConfiguration = ServiceConfiguration(
                    App.getGlobalContext().getString(R.string.base_url),
                    ApiServiceFactory(RestManagerPrivateService::class.java),
                    ApiServiceInterface::class.java)

            privateServiceConfiguration.requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                val builder = Request.Builder().url(request.url()).method(request.method(), request.body())
                val accessToken = "" // set access token
                val tokenType = "" // set token type

                if (tokenType.isNotEmpty() && accessToken.isNotEmpty()) {
                    val currentHeader = Header("Authorization", String.format("%s %s", tokenType, accessToken))
                    builder.addHeader(currentHeader.name.utf8(), currentHeader.value.utf8())
                }
                chain.proceed(builder.build())
            }

            restClientManager = RestClientManager()
            restClientManager!!.addServiceConfiguration(publicServiceConfiguration)
            restClientManager!!.addServiceConfiguration(privateServiceConfiguration)
        }
        return instance as RestClient

    }
}
