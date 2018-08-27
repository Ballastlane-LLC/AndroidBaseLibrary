package com.ballastlane.android.texasam.app.ui.init.emailValidation;

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
import com.ballastlane.android.texasam.app.ui.init.ActivityInit;
import com.ballastlane.android.texasam.app.ui.init.emailValidation.viewmodel.ViewModelEmailValidation;
import com.ballastlane.android.texasam.app.ui.register.ActivityRegister;
import com.ballastlane.android.texasam.databinding.FrgEmailvalidationBinding;
import com.ballastlane.android.texasam.utils.PreferencesUtils;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/17/18.
 */
public class FrgEmailValidation extends BaseFragment implements EventsEmailValidation {

    private FrgEmailvalidationBinding binding;
    private ViewModelEmailValidation viewModel;


    public static FrgEmailValidation newInstance() {

        Bundle args = new Bundle();
        FrgEmailValidation fragment = new FrgEmailValidation();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(ViewModelEmailValidation.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.frg_emailvalidation, container, false);
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
        switch (PreferencesUtils.getInstance().getUserInvitedStatus()) {
            case PreferencesUtils.INVITED_NOT_REGISTERED:
                addDisposable(viewModel.checkEmail(binding.email.getText().toString())
                        .subscribe(new Consumer<Response<ResponseBody>>() {
                            @Override
                            public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                                Timber.d("Code: " + responseBodyResponse.code());

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Timber.d("Error: " + throwable.getMessage());

                            }
                        })
                );

                break;
            case PreferencesUtils.INVITED_REGISTERED:
                goToLogin();
                break;
            default:
                break;
        }

    }

    private void goToLogin() {
        ((ActivityInit) getActivity()).navigateToSecondFragment();
    }

    private void goToRegister() {
        Intent i = new Intent(new Intent(getActivity(),
                ActivityRegister.class));
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        getActivity().finish();
    }
}
