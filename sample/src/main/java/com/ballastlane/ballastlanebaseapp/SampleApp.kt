package com.ballastlane.ballastlanebaseapp

import com.ballastlane.android.baselibrary.app.BaseApp


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class SampleApp : BaseApp() {
    override fun onForeground() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackground() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val TAG = "TAG_${SampleApp::class.java.simpleName}"


    override fun onCreate() {
        super.onCreate()


    }
}