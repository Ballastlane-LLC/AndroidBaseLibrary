package com.ballastlane.android.baselibrary.model.error

import java.io.Serializable

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class ApiError(code: Int) : IllegalStateException(), Serializable {

    var errorCode: Int = 0

    override val message: String? = null

    init {
        this.errorCode = code
    }

    companion object {
        private const val serialVersionUID = -4414321298872148245L
    }
}
