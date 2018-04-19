package com.ballastlane.android.baselibrary.common.base

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ballastlane.android.baselibrary.common.base.busevents.alert.EventAlertDialog
import com.ballastlane.android.baselibrary.common.base.busevents.progress.EventProgressDialog
import com.ballastlane.android.baselibrary.common.base.busevents.snackbar.EventSnackbarMessage
import com.ballastlane.android.baselibrary.common.base.busevents.snackbar.SnackbarEventBuilder
import com.ballastlane.android.baselibrary.common.base.lifecycle.EventBusLifeCycleObserver
import com.ballastlane.android.baselibrary.common.base.lifecycle.RxLifeObserver
import com.ballastlane.android.baselibrary.common.base.navigation.BaseActivityNavigationController
import com.ballastlane.android.baselibrary.common.ext.empty
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseActivity : AppCompatActivity(), BaseEventBusListener, LifecycleRegistryOwner {

    private val TAG = "TAG_${BaseActivity::class.java.simpleName}"

    private val mRegistry = LifecycleRegistry(this)
    private val rxLifeObserver = RxLifeObserver()
    private val busLifeObserver = EventBusLifeCycleObserver(this)

    private var navigationController: Any? = null

    val disposables: CompositeDisposable?
        get() = rxLifeObserver.disposables

    val disposablesForever: CompositeDisposable?
        get() = rxLifeObserver.disposablesForever

    private val viewDecorator: View
        get() = window.decorView

    private var progress: ProgressDialog? = null
        get() {
            if (field == null) {
                field = ProgressDialog(this)
            }
            return field
        }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLifeCycleObservers()
        initVars()
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initListeners()
    }

    override fun getLifecycle(): LifecycleRegistry {
        return this.mRegistry
    }

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
    }

    fun <N : BaseActivityNavigationController> getNavigationController(): N {
        if (navigationController == null) {
            navigationController = BaseActivityNavigationController(supportFragmentManager)
        }
        return navigationController as N
    }

    protected fun <N : BaseActivityNavigationController> setNavigationController(navigationController: N) {
        this.navigationController = navigationController
    }

    protected abstract fun initVars()


    private fun initLifeCycleObservers() {
        lifecycle.addObserver(rxLifeObserver)
        lifecycle.addObserver(busLifeObserver)
    }

    protected abstract fun initViews()

    protected abstract fun initListeners()

    @CallSuper
    @Subscribe
    override fun onProgressDialogEvent(event: EventProgressDialog) {
            buildProgressDialog(event)
    }

    private fun buildProgressDialog(event: EventProgressDialog) {
        progress
        if (event.dismiss) {
            return
        }
        clearKeyboardFromScreen()
        progress!!.setCancelable(event.cancelable)
        progress!!.setMessage(
                if (event.message.empty()) getString(event.messageId) else event.message
        )
        progress!!.show()
    }

    @CallSuper
    @Subscribe
    override fun onAlertDialogEvent(alert: EventAlertDialog) {
        buildAlertDialog(alert)
    }

    private fun buildAlertDialog(alert: EventAlertDialog) {

        val title = if (alert.title.empty()) getString(alert.titleId) else alert.title
        val message = if (alert.message.empty()) getString(alert.messageId) else alert.message
        var positive = if (alert.positiveButtonText.empty()) getString(alert.positiveTextId) else alert.positiveButtonText
        if (positive.empty()) {
            positive = getString(android.R.string.ok)
        }

        var negative = if (alert.negativeButtonText.empty()) getString(alert.negativeTextId) else alert.negativeButtonText
        if (negative.empty()) {
            negative = getString(android.R.string.cancel)
        }

        val builder = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(alert.cancellable)
                .setPositiveButton(positive, alert.positiveListener
                        ?: DialogInterface.OnClickListener { _, _ -> })
        if (alert.negativeListener != null) {
            builder.setNegativeButton(negative, alert.negativeListener)
        }
        builder.show()
    }

    @CallSuper
    @Subscribe
    override fun onSnackbarMessageEvent(event: EventSnackbarMessage) {
        clearKeyboardFromScreen()
        val snackbarEventBuilder = SnackbarEventBuilder(event, viewDecorator)
        snackbarEventBuilder.showSnackbar()
    }

    fun clearKeyboardFromScreen() {
        if (window.currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
        }
    }

    fun openUrlWebPage(url: String, colorId: Int) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, colorId))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    @CallSuper
    override fun onBackPressed() {
        clearKeyboardFromScreen()
        super.onBackPressed()
    }

    fun sendSuccessResult(data: Intent) {
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
