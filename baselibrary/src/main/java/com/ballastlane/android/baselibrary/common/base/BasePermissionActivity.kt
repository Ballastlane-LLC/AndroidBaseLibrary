package com.ballastlane.android.baselibrary.common.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.ballastlane.android.baselibrary.R
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BasePermissionActivity : BaseActivity() {

    abstract val permissions: Array<String>

    abstract val required: Boolean

    private val TAG = "TAG_${BasePermissionActivity::class.java.simpleName}"

    private val acceptedPermissionsSubject = PublishSubject.create<Any>()
    private val rxPermissions by lazy { RxPermissions(this) }

    var requestDisposable: Disposable? = null
    val acceptedPermissionsObservable: Observable<Any> = acceptedPermissionsSubject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        request()
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
}