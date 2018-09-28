package com.ballastlane.android.texasam.app.ui.init;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.databinding.ActivityInitBinding;
import com.ballastlane.android.texasam.utils.PreferencesUtils;

public class ActivityInit extends BaseActivityInnerNavigation {

    private ViewModelActivityInit viewModel;
    private ActivityInitBinding binding;

    public NavigationControllerActivityInit getNavigationControllerInit() {
        return (NavigationControllerActivityInit) super.getNavigationController();
    }

    @Override
    protected void initVars() {
        setNavigationController(new NavigationControllerActivityInit(this));
        PreferencesUtils.getInstance().setUserInvitedStatus(PreferencesUtils.INVITED_NOT_REGISTERED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelActivityInit.class);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_init);
    }

    @Override
    protected void initViews() {
        navigateToFirstFragment();
    }


    @Override
    protected void initListeners() {

    }

    public void navigateToFirstFragment() {
        getNavigationControllerInit().navigateToSection1();
    }

    public void navigateToSecondFragment() {
        getNavigationControllerInit().navigateToSection2();
    }

    @Override
    protected void initializeComponent() {

    }
}
