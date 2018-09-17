package com.ballastlane.android.baselibrary.common.mvp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.FrameLayout
import com.ballastlane.android.baselibrary.common.base.BaseActivity
import com.ballastlane.android.baselibrary.common.utils.showConfirmation
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 9/17/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class MVPView(override val activity: BaseActivity): FrameLayout(activity), MVPContract.View {

    private var progressDialog: ProgressDialog? = null

    override fun showProgress(message: Int, cancelable: Boolean) {
        progressDialog = activity.indeterminateProgressDialog(message)
        progressDialog?.setCancelable(cancelable)
    }

    override fun showProgress(message: String, cancelable: Boolean) {
        progressDialog = activity.indeterminateProgressDialog(message)
        progressDialog?.setCancelable(cancelable)
    }

    override fun hideProgress() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    override fun showToast(message: Int, longTime: Boolean) {
        if (longTime) activity.longToast(message)
        else activity.toast(message)
    }

    override fun showToast(message: String, longTime: Boolean) {
        if (longTime) activity.longToast(message)
        else activity.toast(message)
    }

    override fun showConfirmation(title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog? {
        return showConfirmation(activity, title, message, listener)
    }
}