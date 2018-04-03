package com.ballastlane.ballastlanebaseapp.app.ui.splash

import android.os.Bundle
import com.ballastlane.android.baselibrary.common.base.BaseActivity

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class ActivitySplash : BaseActivity() {
    override fun initViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //  private var binding: ActivitySplashBinding? = null

    override fun initVars() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
    }

//    override fun initViews() {
//        startLogoAnimation()
//        AddAnimationBlink()
//        startWaitingTime()
//    }
//
//    override fun initListeners() {}
//
//    private fun startLogoAnimation() {
//        (binding!!.logo.getDrawable() as Animatable).start()
//    }
//
//    private fun AddAnimationBlink() {
//        addDisposable(
//                Observable
//                        .interval(DEFAULT_BLINK_DELAY_TIME.toLong(), TimeUnit.SECONDS)
//                        .timeInterval()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                {
//                                    if (!(binding!!.logo.getDrawable() as Animatable).isRunning) {
//                                        (binding!!.logo.getDrawable() as Animatable).start()
//                                    }
//                                }
//                        ) { }
//        )
//    }
//
//    private fun startWaitingTime() {
//        addDisposable(
//                Observable
//                        .just(true)
//                        .delay(DEFAULT_DELAY_TIME.toLong(), TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                { startActivity(Intent(this@ActivitySplash, ActivityMain::class.java)) }
//                        ) { }
//        )
//    }
//
//    companion object {
//
//        init {
//            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
//        }
//
//        private val DEFAULT_BLINK_DELAY_TIME = 4
//        private val DEFAULT_DELAY_TIME = 5
//    }

}