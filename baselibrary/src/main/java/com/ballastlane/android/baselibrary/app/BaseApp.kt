package com.ballastlane.android.baselibrary.app

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication
import android.view.inputmethod.InputMethodManager
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
open class BaseApp : MultiDexApplication() {

    private val TAG = "TAG_${BaseApp::class.java.simpleName}"

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

    companion object {
        lateinit var component: AppComponent

        fun closeKeyboard(activity: Activity?) {
            activity?.let {
                val view = activity.currentFocus

                view?.let {
                    val inputManager = activity
                            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }
    }
}