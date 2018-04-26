package com.ballastlane.android.texasam.app.di;

import com.ballastlane.android.baselibrary.app.di.AppScope;
import com.ballastlane.android.baselibrary.app.di.AuthenticationQualifier;
import com.ballastlane.android.baselibrary.app.modules.network.RetrofitModule;
import com.ballastlane.android.texasam.data.managers.remote.base.ApiServiceInterface;


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
    public ApiServiceInterface.Private provideApiServiceInterfaceAuth(@AuthenticationQualifier Retrofit retrofit) {
        return retrofit.create(ApiServiceInterface.Private.class);
    }

    @Provides
    @AppScope
    public ApiServiceInterface.Public provideApiServiceInterface(Retrofit retrofit) {
        return retrofit.create(ApiServiceInterface.Public.class);
    }
}
