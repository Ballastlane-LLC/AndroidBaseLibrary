package com.ballastlane.android.baselibrary.common.base.busevents.alert

import android.content.DialogInterface
import android.support.annotation.StringRes

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/9/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class EventAlertDialog private constructor(builder: Builder) {

    private val TAG = "TAG_${EventAlertDialog::class.java.simpleName}"

    @StringRes
    val titleId: Int
    var title = ""
    @StringRes
    val messageId: Int
    var message = ""
    var cancellable = true
    @StringRes
    val positiveTextId: Int
    val positiveButtonText: String?
    val positiveListener: DialogInterface.OnClickListener?
    @StringRes
    val negativeTextId: Int
    val negativeButtonText: String?
    val negativeListener: DialogInterface.OnClickListener?

    init {
        this.titleId = builder.titleId
        this.title = builder.title
        this.messageId = builder.messageId
        this.message = builder.message
        this.cancellable = builder.cancellable
        this.positiveTextId = builder.positiveTextId
        this.positiveButtonText = builder.positiveButtonText
        this.positiveListener = builder.positiveListener
        this.negativeTextId = builder.negativeTextId
        this.negativeButtonText = builder.negativeButtonText
        this.negativeListener = builder.negativeListener
    }


    class Builder {
        @StringRes
        var titleId: Int = 0
        var title = ""
        @StringRes
        var messageId: Int = 0
        var message = ""
        val cancellable = true
        @StringRes
        var positiveTextId: Int = 0
        var positiveButtonText: String? = null
        var positiveListener: DialogInterface.OnClickListener? = null
        @StringRes
        var negativeTextId: Int = 0
        var negativeButtonText: String? = null
        var negativeListener: DialogInterface.OnClickListener? = null

        fun withTitleId(@StringRes titleId: Int): Builder {
            this.titleId = titleId
            return this
        }

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withMesageId(@StringRes messageId: Int): Builder {
            this.messageId = messageId
            return this
        }

        fun withMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun withPositiveButton(@StringRes positiveTextId: Int, buttonListener: DialogInterface.OnClickListener): Builder {
            this.positiveTextId = positiveTextId
            this.positiveListener = buttonListener
            return this
        }

        fun withPositiveButton(buttonText: String, buttonListener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonText = buttonText
            this.positiveListener = buttonListener
            return this
        }

        fun withNegativeButton(@StringRes negativeTextId: Int, buttonListener: DialogInterface.OnClickListener): Builder {
            this.negativeTextId = negativeTextId
            this.negativeListener = buttonListener
            return this
        }

        fun withNegativeButton(buttonText: String, buttonListener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonText = buttonText
            this.negativeListener = buttonListener
            return this
        }

        fun build(): EventAlertDialog {
            return EventAlertDialog(this)
        }
    }

    companion object {

        val builder: EventAlertDialog.Builder
            get() = EventAlertDialog.Builder()
    }
}