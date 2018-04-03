package com.ballastlane.android.baselibrary.app.modules.network

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.ballastlane.android.baselibrary.app.di.AppQualifier
import com.ballastlane.android.baselibrary.app.di.AppScope
import com.ballastlane.android.baselibrary.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@AppScope
class AuthenticationInterceptor @Inject constructor(
        @AppQualifier val context: Context/*,
        val sessionManager: SessionManager*/
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {

        val request = chain
                ?.request()
                ?.newBuilder()
                ?.build()

        val response = chain?.proceed(request!!)
        val code = response?.code()

        if (code == Constant.Network.Status.UNAUTHORIZED ||
                code == Constant.Network.Status.FORBIDDEN) {
          //  context.apply { startActivity(intentFor<LoginActivity>()) }
        }

        return response
    }
}