package com.ballastlane.android.baselibrary.common.base.busevents.progress

import android.support.annotation.StringRes

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/9/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class EventProgressDialog private constructor(builder: Builder) {

    private val TAG = "TAG_${EventProgressDialog::class.java.simpleName}"

    @StringRes
    val messageId: Int
    var message: String? = ""
    var cancelable = false
    var dismiss = false

    init {
        this.messageId = builder.messageId
        this.message = builder.message
        this.cancelable = builder.cancelable
        this.dismiss = builder.dismiss
    }

    class Builder {

        @StringRes
        var messageId: Int = 0
        var message: String? = null
        var dismiss = false
        var cancelable = false

        fun withMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun withMessageId(@StringRes messageId: Int): Builder {
            this.messageId = messageId
            return this
        }

        fun dismiss(): Builder {
            this.dismiss = true
            return this
        }

        fun withCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun build(): EventProgressDialog {
            return EventProgressDialog(this)
        }
    }

    companion object {

        val builder: Builder
            get() = Builder()
    }

}