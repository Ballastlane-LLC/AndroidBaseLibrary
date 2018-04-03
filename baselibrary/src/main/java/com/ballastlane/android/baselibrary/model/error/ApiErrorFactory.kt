package com.ballastlane.android.baselibrary.model.error

import android.content.Context
import com.ballastlane.android.baselibrary.utils.RestDelegate

import com.google.gson.Gson

import java.net.SocketTimeoutException
import java.net.UnknownHostException

import retrofit2.adapter.rxjava2.HttpException


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class ApiErrorFactory {

    fun parse(ctx: Context, error: Throwable, listener: ApiErrorListener) {
        if (isRelatedToNetwork(error)) {
            listener.onNetworkError(ctx.getString(RestDelegate.instance.noInternetStringID), getNetworkErrorType(error))
        } else if (error is HttpException) {
            try {
                val errorObj = Gson().fromJson(error.response().errorBody()!!.charStream(), ApiError::class.java)
                listener.onApiError(ErrorFactory.getErrorMessage(ctx, errorObj.errorCode), errorObj)
            } catch (e: Throwable) {
                e.printStackTrace()
                listener.onNetworkError(ErrorFactory.getErrorMessage(ctx, ErrorFactory.GENERAL_DEFAULT_ERROR), NetworkErrorType.GENERAL)
            }

        } else if (error is ApiError) {
            listener.onApiError(ErrorFactory.getErrorMessage(ctx, error.errorCode), error)
        }
    }

    fun getNetworkErrorType(error: Throwable): NetworkErrorType {
        if (isNetworkError(error)) {
            return NetworkErrorType.NETWORK
        } else if (isUnknownHostException(error)) {
            return NetworkErrorType.UNKNOWN_HOST
        } else if (isSocketTimeoutException(error)) {
            return NetworkErrorType.SOCKET_TIME_OUT
        }

        return NetworkErrorType.GENERAL
    }

    private fun isNetworkError(error: Throwable): Boolean {
        return error is java.net.ConnectException
    }

    private fun isUnknownHostException(error: Throwable): Boolean {
        return error is UnknownHostException
    }

    private fun isSocketTimeoutException(error: Throwable): Boolean {
        return error is SocketTimeoutException
    }

    fun isRelatedToNetwork(error: Throwable): Boolean {
        return isNetworkError(error) || isSocketTimeoutException(error) || isUnknownHostException(error)
    }
}
