package com.ballastlane.android.baselibrary.common.base.navigation

import android.content.Context
import android.support.annotation.CallSuper
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
open class BaseActivityInnerNavigationController(weakRefActivity: BaseActivityInnerNavigation,
                                            @param:IdRes val innerNavContainerId: Int)
    : BaseActivityNavigationController(weakRefActivity.supportFragmentManager) {

    private val TAG = "TAG_${BaseActivityInnerNavigationController::class.java.simpleName}"


    val titleStack = ArrayList<String>()
    private val weakRefActivity: WeakReference<BaseActivityInnerNavigation> = WeakReference(weakRefActivity)

    val context: Context
        get() = this.weakRefActivity.get()!!.baseContext

    @CallSuper
    fun navigateToRootLevel(frg: Fragment, title: String) {
        BaseFragmentNavigator.cleanFragmentStack(fragmentManager)
        BaseFragmentNavigator.navigateTo(fragmentManager, frg, innerNavContainerId)
        titleStack.clear()
        titleStack.add(title)
        this.weakRefActivity.get()!!.updateActionBarTitle()
    }

    @CallSuper
    fun navigateBackRootLevel() {
        BaseFragmentNavigator.cleanFragmentStack(fragmentManager)
        val firstTitle = titleStack[0]
        titleStack.clear()
        titleStack.add(firstTitle)
        this.weakRefActivity.get()!!.updateActionBarTitle()
    }

    @CallSuper
    fun navigateToLowLevel(frg: Fragment, title: String) {
        titleStack.add(title)
        BaseFragmentNavigator.navigateTo(fragmentManager, frg, innerNavContainerId, true)
        this.weakRefActivity.get()!!.updateActionBarTitle()
    }

}
