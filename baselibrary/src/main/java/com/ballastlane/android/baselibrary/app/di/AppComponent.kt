package com.ballastlane.android.baselibrary.app.di

import android.content.res.Resources
import com.ballastlane.android.baselibrary.app.BaseApp
import com.ballastlane.android.baselibrary.app.modules.MoshiModule
import com.ballastlane.android.baselibrary.app.modules.GlideModule
import com.bumptech.glide.RequestManager
import com.squareup.moshi.Moshi
import dagger.Component

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@AppScope
@Component(modules = [(GlideModule::class), (MoshiModule::class)])
interface AppComponent {
    fun inject(app: BaseApp): Unit
    fun glide(): RequestManager
    fun moshi(): Moshi
    fun resources(): Resources
}