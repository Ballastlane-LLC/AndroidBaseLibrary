package com.ballastlane.ballastlanebaseapp.app.ui.main

import com.kogimobile.baselibrary.sample.R
import java.util.HashMap

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/2/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

class NavigationControllerActivityMain(activity: BaseActivityInnerNavigation) : BaseActivityInnerNavigationController(activity, R.id.container) {

    private var navFragments: HashMap<String, BaseFragment>? = null

    val section1Title: String
        get() = getContext().getString(R.string.nav_drawer_item_section_1)

    val section2Title: String
        get() = getContext().getString(R.string.nav_drawer_item_section_2)

    val section3Title: String
        get() = getContext().getString(R.string.nav_drawer_item_section_3)

    val section4Title: String
        get() = getContext().getString(R.string.nav_drawer_item_section_4)

    val subSection1Title: String
        get() = getContext().getString(R.string.nav_drawer_item_sub_section_1)

    init {
        initFragments()
    }

    private fun initFragments() {
        this.navFragments = HashMap<String, BaseFragment>()
        this.navFragments!![section1Title] = FrgNavigation.newInstance(getFragmentManager().getBackStackEntryCount())
        this.navFragments!![section2Title] = FrgEvents.newInstance()
        this.navFragments!![section3Title] = FrgUtils.newInstance()
        this.navFragments!![section4Title] = FrgRecyclerView.newInstance()
        this.navFragments!![subSection1Title] = TestFragment.newInstance()
    }

    fun navigateToSection1() {
        navigateToRootLevel(this.navFragments!![section1Title], section1Title)
    }

    fun navigateToSection1LowLevel(frg: BaseFragment, title: String) {
        navigateToLowLevel(frg, title)
    }

    fun navigateToSection2() {
        navigateToRootLevel(this.navFragments!![section2Title], section2Title)
    }

    fun navigateToSection3() {
        navigateToRootLevel(this.navFragments!![section3Title], section3Title)
    }

    fun navigateToSection4() {
        navigateToRootLevel(this.navFragments!![section4Title], section4Title)
    }

    fun navigateToSubSection1() {
        navigateToLowLevel(this.navFragments!![subSection1Title], subSection1Title)
    }
}
