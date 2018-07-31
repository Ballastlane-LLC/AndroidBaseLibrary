package com.ballastlane.android.baselibrary.common.base

import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import com.ballastlane.android.baselibrary.R
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BasePermissionActivity : BaseActivity() {

    abstract val permissions: Array<String>

    abstract val required: Boolean

    abstract val dialogPreview: DialogPreview?

    private val TAG = "TAG_${BasePermissionActivity::class.java.simpleName}"

    private val acceptedPermissionsSubject = PublishSubject.create<Any>()
    private val rxPermissions by lazy { RxPermissions(this) }

    var requestDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (dialogPreview != null && !hasPermission()) {
            dialogPreview()
        } else {
            request()
        }
    }

    private fun hasPermission(): Boolean {
        var checkVal = true
        permissions.forEach {
            checkVal = (checkCallingOrSelfPermission(it) == PackageManager.PERMISSION_GRANTED) && checkVal
        }
        return checkVal
    }

    private fun dialogPreview() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setMessage(dialogPreview!!.message)
        val title = TextView(this)
        title.text = dialogPreview!!.title
        title.gravity = Gravity.CENTER
        title.setPadding(60, 80, 60, 10)
        title.textSize = 18F
        alertDialog.setCancelable(false)
        alertDialog.setCustomTitle(title)
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, dialogPreview!!.buttonText, { dialog, _ ->
            request()
            dialog.dismiss()
        })

        alertDialog.show()

        val mw = alertDialog.findViewById<TextView>(android.R.id.message)
        mw!!.gravity = Gravity.CENTER
    }

    private fun request() {

        Log.d(TAG, "request permissions")
        requestDisposable?.dispose()
        requestDisposable = rxPermissions.request(*permissions)
                .subscribe {
                    if (it) {
                        Log.d(TAG, "permissions accepted")
                        acceptedPermissionsSubject.onNext(Any())
                    } else {
                        Log.d(TAG, "permissions rejected")
                        if (required) {
                            showRejectedPermissionsDialog()
                        }
                    }
                }
    }

    private fun showRejectedPermissionsDialog() {
        val title = resources.getString(R.string.permissions_denied_title)
        val message = resources.getString(
                if (required)
                    R.string.alert_permissions_denied_required else
                    R.string.alert_permissions_denied_no_required)
        val buttonText = resources.getString(android.R.string.ok)
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setCancelable(false)
            setTitle(title)
            setMessage(message)
            setNeutralButton(buttonText, { dialog, _ ->
                if (required) {
                    request()
                }
                dialog.dismiss()
            })
        }
        builder.show()
    }

    class DialogPreview(val title: String, val message: String, val buttonText: String) {
        constructor(titleRes: Int, messageRes: Int, buttonTextRes: Int, context: Context) :
                this(context.resources.getString(titleRes),
                        context.resources.getString(messageRes),
                        context.resources.getString(buttonTextRes))
    }
}