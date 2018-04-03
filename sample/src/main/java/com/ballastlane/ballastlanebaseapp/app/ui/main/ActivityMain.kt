package com.ballastlane.ballastlanebaseapp.app.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation
import com.ballastlane.android.baselibrary.common.base.BaseFragment
import com.ballastlane.android.baselibrary.common.base.navigation.BaseFragmentNavigator
import com.kogimobile.baselibrary.sample.R

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class ActivityMain : BaseActivityInnerNavigation() {

    private var binding: ActivityMainBinding? = null

    override fun initVars() {
        setNavigationController(NavigationControllerActivityMain(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun getNavigationController(): NavigationControllerActivityMain {
        return super.getNavigationController<BaseActivityNavigationController>()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (titleStack.size > 1) {
                    return super.onOptionsItemSelected(item)
                } else {
                    binding!!.drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initViews() {
        initToolbar()
        initNavView()
    }

    override fun initListeners() {

    }

    private fun initToolbar() {
        setSupportActionBar(binding!!.includeToolbar.toolbar)
        setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun initNavView() {
        binding!!.navView.setNavigationItemSelectedListener(
                NavigationView.OnNavigationItemSelectedListener { menuItem ->
                    navigateToSection(menuItem.itemId)
                    menuItem.isChecked = true
                    binding!!.drawerLayout.closeDrawers()
                    true
                })
        binding!!.navView.getMenu().getItem(0).setChecked(true)
        navigateToSection1()
    }

    fun navigateToSection1() {
        navigateToSection(R.id.nav_section_1)
    }

    fun navigateSection1LowLevel(frg: BaseFragment, title: String) {
        getNavigationController().navigateToSection1LowLevel(frg, title)
        setNavigationLowLevelStatus()
        updateActionBarTitle()
    }

    fun navigateToSection2() {
        navigateToSection(R.id.nav_section_2)
    }

    fun navigateToSection3() {
        navigateToSection(R.id.nav_section_3)
    }

    fun navigateToSection4() {
        navigateToSection(R.id.nav_section_4)
    }

    fun navigateToSubSection1() {
        navigateToSection(R.id.nav_subsection_1)
    }

    private fun navigateToSection(itemId: Int) {
        BaseFragmentNavigator.cleanFragmentStack(supportFragmentManager)
        titleStack.clear()
        when (itemId) {
            R.id.nav_section_1 -> getNavigationController().navigateToSection1()
            R.id.nav_section_2 -> getNavigationController().navigateToSection2()
            R.id.nav_section_3 -> getNavigationController().navigateToSection3()
            R.id.nav_section_4 -> getNavigationController().navigateToSection4()

            R.id.nav_subsection_1 -> getNavigationController().navigateToSubSection1()
        }
        updateActionBarTitle()
    }

    fun navigateBackRootLevel() {
        getNavigationController().navigateBackRootLevel()
        setNavigationRootStatus()
        updateActionBarTitle()
    }

    private fun setNavigationRootStatus() {
        unLockDrawerLayout()
        setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setNavigationLowLevelStatus() {
        lockDrawerLayout()
        enableHomeBackArrowIndicator()
    }

    private fun lockDrawerLayout() {
        binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun unLockDrawerLayout() {
        binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun onBackPressed() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
            if (titleStack.size == 1) {
                setNavigationRootStatus()
            }
        }
    }

}
