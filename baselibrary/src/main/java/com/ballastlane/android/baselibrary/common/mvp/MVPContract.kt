package com.ballastlane.android.baselibrary.common.mvp

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.BaseActivity

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class MVPContract {

    interface Model

    interface View {
        val activity: BaseActivity
        fun inflateLayout(container: ViewGroup? = null): android.view.View?
        fun showToast(message: String, longTime: Boolean = true)
        fun showToast(message: Int, longTime: Boolean = true)
        fun showProgress(message: String, cancelable: Boolean = false)
        fun showProgress(message: Int, cancelable: Boolean = false)
        fun hideProgress()
        fun showConfirmation(title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog?
    }

    interface Presenter {
        val view: View
        val model: Model
        fun onCreate()
        fun onDestroy()
    }
}