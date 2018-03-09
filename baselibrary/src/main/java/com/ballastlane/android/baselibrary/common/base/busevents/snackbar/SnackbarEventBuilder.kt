package com.ballastlane.android.baselibrary.common.base.busevents.snackbar

import android.support.design.widget.Snackbar
import android.view.View
import com.ballastlane.android.baselibrary.R

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/9/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class SnackbarEventBuilder(private val event: EventSnackbarMessage, view: View) {

    private val TAG = "TAG_${SnackbarEventBuilder::class.java.simpleName}"

    private var viewRoot: View? = null

    private val snackbarByEvent: Snackbar
        get() {
            val snackbar = Snackbar.make(viewRoot!!, messageEvent!!, Snackbar.LENGTH_LONG)
            if (hasSnackbarAction()) {
                snackbar.setAction(actionTextSnackbar, event.actionListener)
            }
            if (hasSnackbarCallback()) {
                snackbar.setCallback(event.callback)
            }
            return snackbar
        }

    private val messageEvent: String?
        get() = if (event.message != null) event.message else viewRoot!!.context.getString(event.messageId)

    private val actionTextSnackbar: String?
        get() =
            if (event.actionText != null) messageEvent else viewRoot!!.context.getString(event.messageId)

    init {
        this.viewRoot = getViewRootToSnackbar(view)
    }

    fun showSnackbar() {
        snackbarByEvent.show()
    }

    private fun isCoordinatorView(view: View): Boolean {
        return view.findViewById<View>(R.id.coordinator) != null
    }

    private fun isViewDifferentToNoneView(event: EventSnackbarMessage): Boolean {
        return event.viewId != EventSnackbarMessage.NONE_VIEW
    }

    private fun hasSnackbarAction(): Boolean {
        return event.actionListener != null
    }

    private fun hasSnackbarCallback(): Boolean {
        return event.callback != null
    }

    private fun getViewRootToSnackbar(view: View): View? {
        if (isViewDifferentToNoneView(event)) {
            viewRoot = view.findViewById(event.viewId)
        } else if (isCoordinatorView(view)) {
            viewRoot = view.findViewById(R.id.coordinator)
        } else {
            viewRoot = view.findViewById(android.R.id.content)
        }
        return viewRoot
    }

}
