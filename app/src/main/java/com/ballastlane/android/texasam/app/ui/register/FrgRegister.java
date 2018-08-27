package com.ballastlane.android.texasam.app.ui.register;

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
import com.ballastlane.android.texasam.app.events.EventsEmailValidation;
import com.ballastlane.android.texasam.app.ui.main.ActivityMain;
import com.ballastlane.android.texasam.app.ui.register.viewmodel.ViewModelRegister;
import com.ballastlane.android.texasam.databinding.FrgRegisterBinding;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */
public class FrgRegister extends BaseFragment implements EventsEmailValidation {

    private FrgRegisterBinding binding;
    private ViewModelRegister viewModel;


    public static FrgRegister newInstance() {
        Bundle args = new Bundle();
        FrgRegister fragment = new FrgRegister();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelRegister.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.frg_register, container, false);
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
    public void onClickContinue() {
        Intent i = new Intent(new Intent(getActivity(),
                ActivityMain.class));
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        getActivity().finish();
    }
}
