package com.ballastlane.android.baselibrary.app.modules.network

import android.content.Context
import com.ballastlane.android.baselibrary.BuildConfig
import com.ballastlane.android.baselibrary.app.di.*
import com.ballastlane.android.baselibrary.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.File

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = [(AppModule::class)])
class NetworkModule {

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
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            authenticationInterceptor: AuthenticationInterceptor,
            cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(authenticationInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()

    @Provides
    @AppScope
    fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
}
