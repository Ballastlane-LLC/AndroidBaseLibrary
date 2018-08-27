package com.ballastlane.android.baselibrary.app

import android.support.multidex.MultiDexApplication
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.AppComponent
import com.ballastlane.android.baselibrary.app.di.AppModule
import com.ballastlane.android.baselibrary.app.di.DaggerAppComponent
import com.ballastlane.android.baselibrary.app.modules.network.AuthenticationInterceptor
import com.ballastlane.android.baselibrary.app.modules.network.NetworkModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class BaseApp : MultiDexApplication() {

    private val TAG = "TAG_${BaseApp::class.java.simpleName}"

    companion object {
        lateinit var component: AppComponent
    }

    lateinit var appModule: AppModule

    override fun onCreate() {
        super.onCreate()

        appModule = AppModule(this)

        component = DaggerAppComponent.builder()
                .appModule(appModule)
                .networkModule(NetworkModule(AuthenticationInterceptor()))
                .build()

        component.inject(this)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}