package com.ballastlane.android.texasam.app.ui.about;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.databinding.FrgAboutBinding;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/17/18.
 */
public class FrgAbout extends BaseFragment {

    private FrgAboutBinding binding;
    private ViewModelAbout viewModel;

    public static FrgAbout newInstance() {

        Bundle args = new Bundle();
        FrgAbout fragment = new FrgAbout();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelAbout.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.frg_about, container, false);
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
}
