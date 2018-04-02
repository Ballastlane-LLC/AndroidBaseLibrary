package com.ballastlane.android.baselibrary.common.mvvm

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.BaseActivity

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class MVVMContract {

    interface Model {}

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

    interface ViewModel {
        val view: View
        val model: Model
    }
}