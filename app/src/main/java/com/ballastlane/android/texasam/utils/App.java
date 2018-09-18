package com.ballastlane.android.texasam.utils;

import com.ballastlane.android.baselibrary.app.BaseApp;
import com.ballastlane.android.baselibrary.app.modules.network.NetworkModule;
import com.ballastlane.android.texasam.app.di.ApiServicesModule;
import com.ballastlane.android.texasam.app.di.DaggerTexasamComponent;
import com.ballastlane.android.texasam.app.di.TexasamComponent;
import com.ballastlane.android.texasam.data.managers.remote.AuthenticationInterceptor;

import timber.log.Timber;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */

public class App extends BaseApp {

    public static TexasamComponent texasamComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        iniTimber();
        initPreferences();

        texasamComponent = DaggerTexasamComponent.builder()
                .networkModule(new NetworkModule(new AuthenticationInterceptor(new Object())))
                .appModule(appModule)
                .apiServicesModule(new ApiServicesModule())
                .build();
    }

    private void initPreferences() {
        PreferencesUtils.init(this);
    }

    private void iniTimber() {
        Timber.plant(
                new Timber.DebugTree() {
                    // Add the line number to the tag.
                    @Override
                    protected String createStackElementTag(StackTraceElement element) {
                        return super.createStackElementTag(element) + ':' + element.getLineNumber();
                    }
                });
    }

    @Override
    public void onBackground() {

    }

    @Override
    public void onForeground() {

    }
}

