package com.ballastlane.android.baselibrary.model

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class ApiServiceFactory<T>(private val serviceClass: Class<T>) : Factory<T> {

    @Throws(IllegalAccessException::class, InstantiationException::class)
    override fun factory(): T {
        return serviceClass.newInstance()
    }

}
