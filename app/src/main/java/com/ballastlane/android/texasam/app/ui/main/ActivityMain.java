package com.ballastlane.android.texasam.app.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ballastlane.android.baselibrary.common.base.BaseActivityInnerNavigation;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.ui.main.viewmodel.ViewModelActivityMain;
import com.ballastlane.android.texasam.databinding.ActivityMainBinding;

public class ActivityMain extends BaseActivityInnerNavigation {

    private ActivityMainBinding binding;
    private ViewModelActivityMain viewModel;


    public NavigationControllerActivityMain getNavigationControllerMain() {
        return (NavigationControllerActivityMain) super.getNavigationController();
    }

    @Override
    protected void initVars() {
        setNavigationController(new NavigationControllerActivityMain(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelActivityMain.class);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        initToolbar();
        navigateToFirstFragment();
        binding.bottombar.setSelectedItemId(R.id.menu_about);
    }

    @Override
    protected void initListeners() {
        binding.bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        navigateToFirstFragment();
                        return true;
                    case R.id.menu_sessions:
                        navigateToSecondFragment();
                        return true;
                    case R.id.menu_payment:
                        navigateToThirdFragment();
                        return true;
                    case R.id.menu_account:
                        navigateToFourthFragment();
                        return true;
                }
                return false;
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

//    private boolean areAllGranted(int[] grantResults) {
//        return grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                && grantResults[1] == PackageManager.PERMISSION_GRANTED;
//    }
//
//    public void showSnackbar(int stringResId) {
//        EventSnackbarMessage.Companion.getBuilder().withMessage(getString(stringResId)).build();
//        EventBus.getDefault().post(EventSnackbarMessage.Companion.getBuilder().withMessage(getString(stringResId)).build());
//    }

    private void navigateToFirstFragment() {
        getNavigationControllerMain().navigateToSection1();
    }

    private void navigateToSecondFragment() {
        getNavigationControllerMain().navigateToSection2();
    }

    private void navigateToThirdFragment() {
        getNavigationControllerMain().navigateToSection3();
    }

    private void navigateToFourthFragment() {
        getNavigationControllerMain().navigateToSection4();
    }


}
