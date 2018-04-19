package com.ballastlane.android.baselibrary.model

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

open class UiStateModel<T> {

    var isInProgress: Boolean = false
    var isError: Boolean = false
    var isSuccess: Boolean = false
    var errorMessage: String? = ""
    var data: T? = null

    constructor() {}

    constructor(inProgress: Boolean, isError: Boolean, isSuccess: Boolean, errorMessage: String?, data: T?) {
        this.isInProgress = inProgress
        this.isError = isError
        this.isSuccess = isSuccess
        this.errorMessage = errorMessage
        this.data = data
    }

    companion object {

        fun <T> idle(): UiStateModel<Any> {
            return UiStateModel<Any>()
        }

        fun <T> loading(): UiStateModel<T> {
            return UiStateModel<T>(true, false, false, null, null)
        }

        fun <T> success(data: T): UiStateModel<T> {
            return UiStateModel<T>(false, false, true, null, data)
        }

        fun <T> failure(errorMessage: String): UiStateModel<T> {
            return UiStateModel<T>(false, true, false, errorMessage, null)
        }
    }
}
