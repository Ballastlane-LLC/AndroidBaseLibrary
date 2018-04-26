package com.ballastlane.android.texasam.app.ui.init.login;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.events.EventsLogin;
import com.ballastlane.android.texasam.app.ui.init.login.viewmodel.ViewModelLogin;
import com.ballastlane.android.texasam.app.ui.main.ActivityMain;
import com.ballastlane.android.texasam.databinding.FrgLoginBinding;

public class FrgLogin extends BaseFragment implements EventsLogin {

    private FrgLoginBinding binding;
    private ViewModelLogin viewModel;

    public static FrgLogin newInstance() {

        Bundle args = new Bundle();
        FrgLogin fragment = new FrgLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelLogin.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.frg_login, container, false);
        this.binding.setEventHandler(this);
        return this.binding.getRoot();
    }

    @Override
    protected void initVars() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClickLogin( ) {
        Intent i = new Intent(new Intent(getActivity(),
                ActivityMain.class));
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onClickSupport() {

    }

    @Override
    public void onClickRecoverPassword( ) {

    }
}