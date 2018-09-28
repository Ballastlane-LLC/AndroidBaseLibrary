package com.ballastlane.android.texasam.app.ui.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.databinding.ActivityRegisterBinding;


public class ActivityRegister extends BaseActivityInnerNavigation {

    private ActivityRegisterBinding binding;


    public NavigationControllerActivityRegister getNavigationControllerRegister() {
        return (NavigationControllerActivityRegister) super.getNavigationController();
    }

    @Override
    protected void initVars() {
        setNavigationController(new NavigationControllerActivityRegister(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    @Override
    protected void initViews() {
        initToolbar();
        navigateToFirstFragment();
    }

    @Override
    protected void initListeners() {

    }

    private void initToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar);
        getSupportActionBar().setTitle(getString(R.string.nav_register));
    }


    private void navigateToFirstFragment() {
        getNavigationControllerRegister().navigateToSection1();
    }


    @Override
    protected void initializeComponent() {

    }
}
