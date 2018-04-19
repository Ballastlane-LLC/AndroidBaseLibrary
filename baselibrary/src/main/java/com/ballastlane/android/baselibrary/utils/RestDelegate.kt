package com.ballastlane.android.baselibrary.utils

import com.ballastlane.android.baselibrary.R


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

open class RestDelegate {

    val noInternetStringID = R.string.network_error_no_internet_connection

    companion object {

        private var _delegate: RestDelegate? = null

        val instance: RestDelegate
            get() {
                if (_delegate == null) {
                    _delegate = RestDelegate()
                }
                return _delegate as RestDelegate
            }
    }

}
