package com.ballastlane.ballastlanebaseapp.data

import com.kogimobile.baselibrary.sample.data.repositories.user.RepositoryUser

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/3/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class Injection {

    val userRepository: RepositoryUser
        get() = RepositoryUser.newInstance()

}
