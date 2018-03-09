package com.ballastlane.android.baselibrary.common.base.busevents.snackbar

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/9/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class EventSnackbarMessage {

    private val TAG = "TAG_${EventSnackbarMessage::class.java.simpleName}"

    var message: String? = null
    @StringRes
    var messageId: Int = 0
    var actionText: String? = null
    @StringRes
    var actionTextId: Int = 0
    var viewId = NONE_VIEW
    var actionListener: View.OnClickListener? = null
    var callback: Snackbar.Callback? = null

    constructor() {}

    private constructor(builder: Builder) {
        this.message = builder.message
        this.messageId = builder.messageId
        this.actionText = builder.actionText
        this.actionTextId = builder.actionTextId
        this.viewId = builder.viewId
        this.actionListener = builder.actionListener
        this.callback = builder.callback
    }

    class Builder {

        var message: String? = null
        @StringRes
        var messageId: Int = 0
        var actionText: String? = null
        @StringRes
        var actionTextId: Int = 0
        var viewId = NONE_VIEW
        var actionListener: View.OnClickListener? = null
        var callback: Snackbar.Callback? = null

        fun withMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun withMessage(@StringRes messageId: Int): Builder {
            this.messageId = messageId
            return this
        }

        fun withActionText(actionText: String): Builder {
            this.actionText = actionText
            return this
        }

        fun withActionText(@StringRes actionTextId: Int): Builder {
            this.actionTextId = actionTextId
            return this
        }

        fun withViewId(viewId: Int): Builder {
            this.viewId = viewId
            return this
        }

        fun withActionListener(actionListener: View.OnClickListener): Builder {
            this.actionListener = actionListener
            return this
        }

        fun withCallback(callback: Snackbar.Callback): Builder {
            this.callback = callback
            return this
        }

        fun build(): EventSnackbarMessage {
            return EventSnackbarMessage(this)
        }
    }

    companion object {

        val NONE_VIEW = -1

        val builder: EventSnackbarMessage.Builder
            get() = EventSnackbarMessage.Builder()
    }

}
