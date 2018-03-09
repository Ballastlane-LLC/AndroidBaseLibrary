package com.ballastlane.android.baselibrary.utils

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

interface Constant {

    interface Location {
        companion object {
            val INTERVAL: Long = 60000
        }
    }

    interface Key {
        companion object {
            val ACCESS_TOKEN = "access_token"
            val AUTHORIZATION = "Authorization"
            val JWT = "JWT "
            val BEARER = "Bearer "

            val ID = "id"
            val ID_STR = "id_str"
            val NAME = "name"

            val USERS = "users"

        }
    }

    interface Models {
        companion object {
            val AUTH = "auth"
        }
    }

    interface Preferences {
        companion object {
            val APP = "baseAppPreferences"
            val SESSION = "baseAppSessionPreferences"
            val CACHE = "okhttp_cache"
        }
    }

    interface Network {
        interface Status {
            companion object {
                val SUCCESS = 200
                val CREATED = 201
                val BAD_REQUEST = 400
                val UNAUTHORIZED = 401
                val FORBIDDEN = 403
                val NOT_FOUND = 404
                val CONFLICT = 409
            }
        }
    }

    interface Url {

        interface Character {
            companion object {
                val BAR = "/"
                val PARAMETER_OPEN = "{"
                val PARAMETER_CLOSE = "}"
                val QUERY = "?"
                val EQUAL = "="
                val OTHER_QUERY = "&"
            }
        }

        interface Auth {
            companion object {
                val BASE = Character.BAR + Models.AUTH
            }
        }


        companion object {

            val PLAY = "https://play.google.com/store"
        }
    }

}