package com.ballastlane.android.baselibrary.app.modules

import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.app.di.FlatObjectsQualifier
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module
class MoshiModule {
    @Provides
    @AppScope
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @AppScope
    @FlatObjectsQualifier
    fun provideMoshiFlatObjects(): Moshi {
        return Moshi.Builder()
                .build()
    }
}