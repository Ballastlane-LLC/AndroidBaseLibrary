package com.ballastlane.android.baselibrary.app.modules.network

import android.content.res.Resources
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.app.di.AuthenticationQualifier
import com.ballastlane.android.baselibrary.app.di.BaseUrlQualifier
import com.ballastlane.android.baselibrary.app.di.FlatObjectsQualifier
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = [(NetworkModule::class)])
class RetrofitModule {

    @Provides
    @AppScope
    @BaseUrlQualifier
    fun provideBaseUrl(resources: Resources): String = resources.getString(R.string.url_api_current)

    @Provides
    @AppScope
    fun provideConverterFactory(@FlatObjectsQualifier moshi: Moshi): Converter.Factory =
            MoshiConverterFactory.create(moshi)

    @Provides
    @AppScope
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedRetrofit(
            @BaseUrlQualifier baseUrl: String,
            @AuthenticationQualifier client: OkHttpClient,
            converter: Converter.Factory,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(callAdapter)
                    .build()

    @Provides
    @AppScope
    fun provideRetrofit(
            @BaseUrlQualifier baseUrl: String,
            client: OkHttpClient,
            converter: Converter.Factory,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(callAdapter)
                    .build()
}