package com.ballastlane.android.texasam.app.ui.register;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.baselibrary.common.base.navigation.BaseActivityInnerNavigationController;
import com.ballastlane.android.texasam.R;

import java.util.HashMap;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/24/18.
 */
class NavigationControllerActivityRegister extends BaseActivityInnerNavigationController {

    private HashMap<String, BaseFragment> navFragments;

    public NavigationControllerActivityRegister(BaseActivityInnerNavigation activity) {
        super(activity, R.id.container);
        initFragments();
    }

    private void initFragments() {
        this.navFragments = new HashMap<>();
        this.navFragments.put(getSection1Title(), FrgRegister.newInstance());
    }

    private String getSection1Title() {
        return getContext().getString(R.string.nav_register);
    }


    public void navigateToSection1() {
        navigateToRootLevel(this.navFragments.get(getSection1Title()), getSection1Title());
    }


}
