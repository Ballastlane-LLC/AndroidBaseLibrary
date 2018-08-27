package com.ballastlane.android.baselibrary.app.modules.network

import android.content.Context
import android.content.res.Resources
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.*
import com.ballastlane.android.baselibrary.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = [(AppModule::class)])
class NetworkModule(private val authenticationInterceptor: Interceptor) {

    @Provides
    @AppScope
    @CacheQualifier
    fun provideCacheFile(@AppQualifier context: Context): File =
            File(context.cacheDir, Constant.Preferences.CACHE)

    @Provides
    @AppScope
    @CacheQualifier
    fun provideCacheMaxSize(): Long = 10 * 1024 * 1024

    @Provides
    @AppScope
    fun provideCache(@CacheQualifier file: File, @CacheQualifier maxSize: Long): Cache =
            Cache(file, maxSize)

    @Provides
    @AppScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(Level.BODY)
    }

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            cache: Cache,
            resources: Resources): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(authenticationInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(
                            resources.getInteger(R.integer.read_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .writeTimeout(
                            resources.getInteger(R.integer.write_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .connectTimeout(
                            resources.getInteger(R.integer.connect_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .build()

    @Provides
    @AppScope
    fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            cache: Cache,
            resources: Resources): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(
                            resources.getInteger(R.integer.read_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .writeTimeout(
                            resources.getInteger(R.integer.write_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .connectTimeout(
                            resources.getInteger(R.integer.connect_timeout_seconds).toLong(),
                            TimeUnit.SECONDS)
                    .build()
}
