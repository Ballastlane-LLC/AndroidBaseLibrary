package com.ballastlane.android.baselibrary.app.modules.network

import android.content.res.Resources
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.app.di.AuthenticationQualifier
import com.ballastlane.android.baselibrary.app.di.BaseUrlQualifier
import com.ballastlane.android.baselibrary.utils.CollectionTypedAdapter
import com.ballastlane.android.baselibrary.utils.DateTimeConverter
import com.ballastlane.android.baselibrary.utils.DoubleTypedAdapter
import com.ballastlane.android.baselibrary.utils.LongTypedAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @AppScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeConverter())
        gsonBuilder.registerTypeHierarchyAdapter(Collection::class.java, CollectionTypedAdapter())
        gsonBuilder.registerTypeHierarchyAdapter(Long::class.java, LongTypedAdapter())
        gsonBuilder.registerTypeHierarchyAdapter(Double::class.java, DoubleTypedAdapter())
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        return gsonBuilder.create()
    }

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedRetrofit(
            @BaseUrlQualifier baseUrl: String,
            @AuthenticationQualifier client: OkHttpClient,
            gson: Gson,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(callAdapter)
                    .build()

    @Provides
    @AppScope
    fun provideRetrofit(
            @BaseUrlQualifier baseUrl: String,
            client: OkHttpClient,
            gson: Gson,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(callAdapter)
                    .build()
}
