package com.ballastlane.android.baselibrary.app.modules

import android.content.Context
import com.ballastlane.android.baselibrary.app.di.AppModule
import com.ballastlane.android.baselibrary.app.di.AppQualifier
import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.app.modules.network.NetworkModule
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.io.InputStream


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = [(NetworkModule::class), (AppModule::class)])
class GlideModule {

    @Provides
    @AppScope
    fun provideGlide(@AppQualifier context: Context, downloader: OkHttpClient): RequestManager {
        Glide.get(context).registry.replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(downloader))
        return Glide.with(context)
    }

}