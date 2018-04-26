package com.ballastlane.ballastlanebaseapp.di.di;

import com.ballastlane.android.baselibrary.app.di.AppScope;
import com.ballastlane.android.baselibrary.app.modules.network.RetrofitModule;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/26/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
@Module(includes = {RetrofitModule.class})
public class ApiServicesModule {
    @Provides
    @AppScope
    public ApiServiceInterface provideApiServiceInterface(Retrofit retrofit) {
        return retrofit.create(ApiServiceInterface.class);
    }
}

