package com.ballastlane.android.baselibrary.app

import android.content.ComponentCallbacks2
import android.content.pm.PackageManager
import android.support.multidex.MultiDexApplication
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.di.AppComponent
import com.ballastlane.android.baselibrary.app.di.AppModule
import com.ballastlane.android.baselibrary.app.di.DaggerAppComponent
import com.ballastlane.android.baselibrary.app.modules.network.AuthenticationInterceptor
import com.ballastlane.android.baselibrary.app.modules.network.NetworkModule
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseApp : MultiDexApplication() {

    private val TAG = "TAG_${BaseApp::class.java.simpleName}"

    lateinit var appModule: AppModule

    override fun onCreate() {
        super.onCreate()

        instance = this

        status = StatusApp.FOREGROUND

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

        iniTimber()
    }

    private fun iniTimber() {
        Timber.plant(
                object : Timber.DebugTree() {
                    override fun createStackElementTag(element: StackTraceElement): String? {
                        return super.createStackElementTag(element) + ':'.toString() + element.lineNumber
                    }
                })
    }

    @Synchronized
    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            onBackground()
            status = StatusApp.BACKGROUND
        }
        super.onTrimMemory(level)
    }

    abstract fun onBackground()

    abstract fun onForeground()

    private enum class StatusApp {
        BACKGROUND,
        FOREGROUND
    }

    companion object {

        lateinit var component: AppComponent

        private lateinit var status: StatusApp

        private lateinit var instance: BaseApp

        @Synchronized
        fun onResumeActivity() {
            if (status === StatusApp.BACKGROUND) {
                instance.onForeground()
            }
            status = StatusApp.FOREGROUND
        }

        fun getVersionName(): String {
            return try {
                instance.packageManager.getPackageInfo(instance.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                ""
            }

        }
    }
}