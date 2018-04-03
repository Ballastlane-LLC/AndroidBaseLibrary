package com.ballastlane.ballastlanebaseapp.app

import com.ballastlane.android.baselibrary.app.BaseApp
import com.ballastlane.ballastlanebaseapp.older.di.ApiComponent
import com.ballastlane.ballastlanebaseapp.older.di.ApiModule
import com.ballastlane.ballastlanebaseapp.di.DaggerApiComponent
import timber.log.Timber


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

        initTimber()
    }

    private fun initTimber() {
        Timber.plant(
                object : Timber.DebugTree() {
                    // Add the line number to the tag.
                    override fun createStackElementTag(element: StackTraceElement): String {
                        return super.createStackElementTag(element) + ':'.toString() + element.lineNumber
                    }
                })
    }
}