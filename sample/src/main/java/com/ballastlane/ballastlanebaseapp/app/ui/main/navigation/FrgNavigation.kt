package com.ballastlane.ballastlanebaseapp.app.ui.main.navigation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballastlane.android.baselibrary.common.base.BaseFragment
import com.ballastlane.ballastlanebaseapp.app.ui.main.ActivityMain
import com.kogimobile.baselibrary.sample.R

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

open class FrgNavigation : BaseFragment(), EventHandlerNavigation {

    private var binding: FrgNavigationBinding? = null

    private var navigationLevel: Int = 0

    protected fun initVars() {
        if (arguments.containsKey(ARG_NAVIGATION_LEVEL)) {
            this.navigationLevel = getArguments().getInt(ARG_NAVIGATION_LEVEL)
        }
    }

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {
        this.binding = DataBindingUtil.inflate<FrgNavigationBinding>(inflater, R.layout.frg_navigation, container, false)
        this.binding!!.setEventHandler(this)
        return this.binding!!.getRoot()
    }

    protected override fun initViews() {
        setupNavigationLevel()
        checkNavigateRootVisibility()
    }

    protected override fun initListeners() {

    }

    private fun setupNavigationLevel() {
        this.binding!!.description.setText(getString(R.string.frg_navigation_description, navigationLevel))
    }

    private fun checkNavigateRootVisibility() {
        if (navigationLevel > 0) {
            this.binding!!.buttonRoot.setVisibility(View.VISIBLE)
        } else {
            this.binding!!.buttonRoot.setVisibility(View.GONE)
        }
    }

    override fun onClickRoot() {
        (activity as ActivityMain).navigateBackRootLevel()
    }

    override fun onClickLowLevel() {
        val stackCount = activity.getSupportFragmentManager().backStackEntryCount + 1
        (activity as ActivityMain)
                .navigateSection1LowLevel(
                        FrgNavigation.newInstance(stackCount),
                        String.format("%s %d", getString(R.string.frg_navigation_title), stackCount)
                )
    }

    companion object {

        const val ARG_NAVIGATION_LEVEL = "ARG_NAVIGATION_LEVEL"

        fun newInstance(navigationLevel: Int): FrgNavigation {
            val frg = FrgNavigation()
            val args = Bundle()
            args.putInt(ARG_NAVIGATION_LEVEL, navigationLevel)
            frg.arguments = args
            return frg
        }
    }
}
