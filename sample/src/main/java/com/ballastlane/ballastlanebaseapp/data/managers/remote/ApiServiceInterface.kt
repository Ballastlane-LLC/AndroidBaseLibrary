package com.ballastlane.ballastlanebaseapp.data.managers.remote

import com.ballastlane.ballastlanebaseapp.data.repositories.user.source.RestUser
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

interface ApiServiceInterface {

    @POST("users")
    fun createUser(@Body user: RestUser): Single<RestUser>

    @PATCH("users")
    fun updateUser(@Body request: RestUser): Single<RestUser>

    @GET("users/{user_id}")
    fun getUser(@Path("user_id") userId: String): Single<RestUser>

}