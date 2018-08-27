package com.ballastlane.android.baselibrary.model.error

import android.content.Context
import com.ballastlane.android.baselibrary.R

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
internal class ErrorFactory {


    companion object {
        const val GENERAL_DEFAULT_ERROR = -1

        fun getErrorMessage(ctx: Context, code: Int): String {
            return try {
                var errorCodeMapping = code.toString()
                errorCodeMapping = errorCodeMapping.replace("-", "_")
                val id = ctx.resources.getIdentifier(
                        "error_code_$errorCodeMapping",
                        "string", ctx.packageName)
                ctx.getString(id)
            } catch (e: Exception) {
                ctx.getString(R.string.network_error_code_general)
            }

        }
    }

}
