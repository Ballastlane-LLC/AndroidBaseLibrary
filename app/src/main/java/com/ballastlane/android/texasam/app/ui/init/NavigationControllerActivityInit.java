package com.ballastlane.android.texasam.app.ui.init;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.baselibrary.common.base.navigation.BaseActivityInnerNavigationController;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.ui.init.emailValidation.FrgEmailValidation;
import com.ballastlane.android.texasam.app.ui.init.login.FrgLogin;

import java.util.HashMap;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */
public class NavigationControllerActivityInit extends BaseActivityInnerNavigationController {

    private HashMap<String, BaseFragment> navFragments;

    public NavigationControllerActivityInit(BaseActivityInnerNavigation activity) {
        super(activity, R.id.container);
        initFragments();
    }

    private void initFragments() {
        this.navFragments = new HashMap<>();
        this.navFragments.put(getSection1InitTitle(), FrgEmailValidation.newInstance());
        this.navFragments.put(getSection2InitTitle(), FrgLogin.newInstance());
    }

    private String getSection1InitTitle() {
        return getContext().getString(R.string.nav_validate_email);
    }

    private String getSection2InitTitle() {
        return getContext().getString(R.string.nav_login);
    }

    public void navigateToSection1() {
        navigateToRootLevel(this.navFragments.get(getSection1InitTitle()), getSection1InitTitle());
    }

    public void navigateToSection2() {
        navigateToRootLevel(this.navFragments.get(getSection2InitTitle()), getSection2InitTitle());
    }

}
