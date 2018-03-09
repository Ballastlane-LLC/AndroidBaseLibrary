package com.ballastlane.android.baselibrary.app.modules

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.ballastlane.android.baselibrary.app.di.AppModule
import com.ballastlane.android.baselibrary.app.di.AppQualifier
import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.app.modules.network.NetworkModule
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = arrayOf(NetworkModule::class, AppModule::class))
class PicassoModule {

    @Provides
    @AppScope
    fun provideDownloader(okHttpClient: OkHttpClient): Downloader = OkHttp3Downloader(okHttpClient)

    @Provides
    @AppScope
    fun providePicasso(@AppQualifier context: Context, downloader: Downloader): Picasso =
            Picasso.Builder(context)
                    .downloader(downloader)
                    .build()
}