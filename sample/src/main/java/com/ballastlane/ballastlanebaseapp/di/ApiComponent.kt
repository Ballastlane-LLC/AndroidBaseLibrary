package com.ballastlane.ballastlanebaseapp.di

import com.ballastlane.android.baselibrary.app.BaseApp
import com.ballastlane.android.baselibrary.app.di.AppScope
import dagger.Component

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@AppScope
@Component(modules = [(ApiModule::class)])
interface ApiComponent {
    fun inject(app: BaseApp): Unit

}