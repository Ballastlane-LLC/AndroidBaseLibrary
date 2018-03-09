package com.ballastlane.android.baselibrary.common.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
class BaseFragmentNavigator {

    private val TAG = "TAG_${BaseFragmentNavigator::class.java.simpleName}"

    companion object {
        fun navigateTo(manager: FragmentManager, fragment: Fragment, containerId: Int, addToBackStack: Boolean) {
            navigateTo(
                    manager,
                    fragment,
                    containerId,
                    fragment.javaClass.simpleName,
                    addToBackStack,
                    false
            )
        }

        @JvmOverloads
        fun navigateTo(manager: FragmentManager,
                       fragment: Fragment,
                       containerId: Int,
                       fragmentTag: String = fragment.javaClass.simpleName,
                       addToBackStack: Boolean = false,
                       allowCommitStateLoss: Boolean = false) {
            val ft = manager.beginTransaction()
            ft.replace(containerId, fragment, fragmentTag)

            if (addToBackStack) {
                ft.addToBackStack(fragmentTag)
            }

            if (allowCommitStateLoss) {
                ft.commitAllowingStateLoss()
            } else {
                ft.commit()
            }
        }

        fun cleanFragmentStack(fm: FragmentManager) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
