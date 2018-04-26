package com.ballastlane.android.texasam.app.ui.main;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.baselibrary.common.base.navigation.BaseActivityInnerNavigationController;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.ui.about.FrgAbout;
import com.ballastlane.android.texasam.app.ui.account.FrgAccount;
import com.ballastlane.android.texasam.app.ui.payment.FrgPayments;
import com.ballastlane.android.texasam.app.ui.sessions.FrgSessions;

import java.util.HashMap;


public class NavigationControllerActivityMain extends BaseActivityInnerNavigationController {

    private HashMap<String, BaseFragment> navFragments;

    public NavigationControllerActivityMain(BaseActivityInnerNavigation activity) {
        super(activity, R.id.container);
        initFragments();
    }

    private void initFragments() {
        this.navFragments = new HashMap<>();
        this.navFragments.put(getSection1Title(), FrgAbout.newInstance());
        this.navFragments.put(getSection2Title(), FrgSessions.newInstance());
        this.navFragments.put(getSection3Title(), FrgPayments.newInstance());
        this.navFragments.put(getSection4Title(), FrgAccount.newInstance());
    }

    private String getSection1Title() {
        return getContext().getString(R.string.nav_about);
    }

    private String getSection2Title() {
        return getContext().getString(R.string.nav_surveys);
    }


    private String getSection3Title() {
        return getContext().getString(R.string.nav_payments);
    }

    private String getSection4Title() {
        return getContext().getString(R.string.nav_account);
    }

    public void navigateToSection1() {
        navigateToRootLevel(this.navFragments.get(getSection1Title()), getSection1Title());
    }

    public void navigateToSection2() {
        navigateToRootLevel(this.navFragments.get(getSection2Title()), getSection2Title());
    }

    public void navigateToSection3() {
        navigateToRootLevel(this.navFragments.get(getSection3Title()), getSection3Title());
    }

    public void navigateToSection4() {
        navigateToRootLevel(this.navFragments.get(getSection4Title()), getSection4Title());
    }

}
