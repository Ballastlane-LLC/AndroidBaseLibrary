package com.ballastlane.android.baselibrary.model.error



/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
interface ApiErrorListener {

    fun onNetworkError(errorMessage: String, type: NetworkErrorType)

    fun onApiError(errorMessage: String, error: ApiError)

}
