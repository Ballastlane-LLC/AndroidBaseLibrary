package com.ballastlane.android.baselibrary.common.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class BaseActivityNavigationController(val fragmentManager: FragmentManager) {

    private val TAG = "TAG_${BaseActivityNavigationController::class.java.simpleName}"


    fun navigateTo(fragment: Fragment, containerId: Int) {
        navigateTo(
                fragment,
                containerId,
                fragment.javaClass.simpleName,
                false,
                false
        )
    }

    fun navigateTo(fragment: Fragment, containerId: Int, addToBackStack: Boolean) {
        navigateTo(
                fragment,
                containerId,
                fragment.javaClass.simpleName,
                addToBackStack,
                false
        )
    }

    private fun navigateTo(fragment: Fragment, containerId: Int, fragmentTag: String, addToBackStack: Boolean, allowCommitStateLoss: Boolean) {
        val ft = fragmentManager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(fragmentTag)
        }

        ft.replace(containerId, fragment, fragmentTag)

        if (allowCommitStateLoss) {
            ft.commitAllowingStateLoss()
        } else {
            ft.commit()
        }
    }

    fun cleanFragmentStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}