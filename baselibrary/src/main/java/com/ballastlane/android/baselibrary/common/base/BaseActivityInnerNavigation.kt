package com.ballastlane.android.baselibrary.common.base

import android.graphics.drawable.Drawable
import android.support.annotation.CallSuper
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import com.ballastlane.android.baselibrary.common.base.navigation.BaseActivityInnerNavigationController
import java.util.*

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
abstract class BaseActivityInnerNavigation : BaseActivity() {

    private val TAG = "TAG_${BaseActivityInnerNavigation::class.java.simpleName}"

    private var homeUpIndicator = HOME_UP_INDICATOR_NONE

    val titleStack: ArrayList<String>
        get() = (getNavigationController<BaseActivityInnerNavigationController>()).titleStack

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (getNavigationController<BaseActivityInnerNavigationController>()
                                .titleStack.size == 0 &&
                        homeUpIndicator != HOME_UP_INDICATOR_NONE ||
                        getNavigationController<BaseActivityInnerNavigationController>()
                                .titleStack.size > 0) {
                    onBackPressed()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        updateActionBarTitle()
    }

    @CallSuper
    fun updateActionBarTitle() {
        if (supportActionBar != null) {
            if (getNavigationController<BaseActivityInnerNavigationController>().titleStack.size > 0) {
                supportActionBar!!.title = getNavigationController<BaseActivityInnerNavigationController>()
                        .titleStack[getNavigationController<BaseActivityInnerNavigationController>()
                        .titleStack.size - 1]
                updateActionBarUpIndicator()
            }
        }
    }

    @CallSuper
    fun updateActionBarUpIndicator() {
        if (getNavigationController<BaseActivityInnerNavigationController>().titleStack.size > 1) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            if (homeUpIndicator != HOME_UP_INDICATOR_NONE) {
                val upIndicator: Drawable = if (homeUpIndicator != HOME_UP_INDICATOR_ARROW) {
                    ContextCompat.getDrawable(this, homeUpIndicator)!!
                } else {
                    drawerToggleDelegate!!.themeUpIndicator
                }
                supportActionBar!!.setHomeAsUpIndicator(upIndicator)
            }
        } else {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
        if (homeUpIndicator != HOME_UP_INDICATOR_NONE) {
            if (homeUpIndicator != HOME_UP_INDICATOR_ARROW) {
                supportActionBar!!.setHomeAsUpIndicator(ContextCompat.getDrawable(
                        this,
                        homeUpIndicator))
            } else {
                supportActionBar!!.setHomeAsUpIndicator(drawerToggleDelegate!!.themeUpIndicator)
            }
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        } else {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        }
    }

    @CallSuper
    protected fun setHomeAsUpIndicator(resourceId: Int) {
        homeUpIndicator = resourceId
        updateActionBarUpIndicator()
    }

    @CallSuper
    protected fun enableHomeBackArrowIndicator() {
        homeUpIndicator = HOME_UP_INDICATOR_ARROW
        updateActionBarUpIndicator()
    }

    @CallSuper
    override fun onBackPressed() {
        super.onBackPressed()
        if (getNavigationController<BaseActivityInnerNavigationController>().titleStack.size > 0) {
            getNavigationController<BaseActivityInnerNavigationController>().titleStack
                    .removeAt(getNavigationController<BaseActivityInnerNavigationController>()
                            .titleStack.size - 1)
            if (getNavigationController<BaseActivityInnerNavigationController>().titleStack.size > 0) {
                updateActionBarTitle()
            }
        }
    }

    companion object {

        private val HOME_UP_INDICATOR_NONE = -1
        private val HOME_UP_INDICATOR_ARROW = 0
    }

}
