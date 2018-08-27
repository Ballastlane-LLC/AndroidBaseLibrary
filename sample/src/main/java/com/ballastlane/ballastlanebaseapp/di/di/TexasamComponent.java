package com.ballastlane.ballastlanebaseapp.di.di;

import com.ballastlane.android.baselibrary.app.di.AppScope;

import dagger.Component;


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/26/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

@AppScope
@Component(modules = {ApiServicesModule.class})
public interface TexasamComponent {
    ApiServiceInterface serviceInterfaceApi();
}
