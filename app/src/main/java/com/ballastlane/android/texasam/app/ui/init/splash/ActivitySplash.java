package com.ballastlane.android.texasam.app.ui.init.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.ballastlane.android.baselibrary.common.base.BaseActivity;
import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.app.ui.init.ActivityInit;
import com.ballastlane.android.texasam.app.ui.main.ActivityMain;
import com.ballastlane.android.texasam.databinding.ActivitySplashBinding;
import com.ballastlane.android.texasam.utils.PreferencesUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Julian Cardona on 6/23/16.
 */
public class ActivitySplash extends BaseActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final int DEFAULT_DELAY_TIME = 3;

    private ActivitySplashBinding binding;


    @Override
    protected void initVars() {
        PreferencesUtils.getInstance().setUserLogged(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    protected void initViews() {
        startWaitingTime();
    }


    @Override
    protected void initListeners() {

    }

    private void startWaitingTime() {
        addDisposable(
                Observable
                        .just(true)
                        .delay(DEFAULT_DELAY_TIME, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        startActivity(new Intent(ActivitySplash.this,
                                                PreferencesUtils.getInstance().isUserLogged() ?
                                                        ActivityMain.class :
                                                        ActivityInit.class));
                                    }
                                }
                                ,
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {

                                    }
                                }
                        )
        );
    }

}