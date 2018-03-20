package com.ballastlane.ballastlanebaseapp

import com.ballastlane.android.baselibrary.app.BaseApp
import com.ballastlane.ballastlanebaseapp.di.ApiComponent
import com.ballastlane.ballastlanebaseapp.di.ApiModule
import com.ballastlane.ballastlanebaseapp.di.DaggerApiComponent


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class SampleApp : BaseApp() {

    private val TAG = "TAG_${SampleApp::class.java.simpleName}"

    companion object {
        lateinit var api: ApiComponent
    }

    override fun onCreate() {
        super.onCreate()

        api = DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .build()

        api.inject(this)

    }
}