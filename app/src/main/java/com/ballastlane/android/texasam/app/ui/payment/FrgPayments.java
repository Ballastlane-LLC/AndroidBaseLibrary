package com.ballastlane.android.texasam.app.ui.payment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ballastlane.android.baselibrary.common.base.BaseFragment;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.ui.payment.viewmodel.ViewModelPayment;
import com.ballastlane.android.texasam.databinding.FrgPaymentsBinding;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/17/18.
 */
public class FrgPayments extends BaseFragment {

    private FrgPaymentsBinding binding;
    private ViewModelPayment viewModel;


    public static FrgPayments newInstance() {

        Bundle args = new Bundle();
        FrgPayments fragment = new FrgPayments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelPayment.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.frg_payments, container, false);
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
