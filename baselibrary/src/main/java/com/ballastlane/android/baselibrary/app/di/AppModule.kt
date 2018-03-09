package com.ballastlane.android.baselibrary.app.di

import android.content.Context
import android.content.res.Resources
import com.ballastlane.android.baselibrary.app.BaseApp
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module
class AppModule(private val app: BaseApp) {

    @Provides
    @AppScope
    fun provideApp(): BaseApp = app

    @Provides
    @AppScope
    @AppQualifier
    fun provideApplicationContext(): Context = app

    @Provides
    @AppScope
    fun provideResources(): Resources = app.resources
}