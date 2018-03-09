package com.ballastlane.android.baselibrary.app

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.AppComponent
import com.ballastlane.android.baselibrary.app.di.AppModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class BaseApp : MultiDexApplication() {

    private val TAG = "TAG_${BaseApp::class.java.simpleName}"

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

//        component = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .build()
//
//        component.inject(this)
//
//        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
//                .setDefaultFontPath(getString(R.string.font_regular))
//                .setFontAttrId(R.attr.fontPath)
//                .build())
    }
}