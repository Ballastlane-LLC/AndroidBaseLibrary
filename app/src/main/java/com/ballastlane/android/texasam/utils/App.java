package com.ballastlane.android.texasam.utils;

import android.content.Context;

import com.ballastlane.android.baselibrary.app.BaseApp;

import timber.log.Timber;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        iniTimber();
        initPreferences();
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
}

