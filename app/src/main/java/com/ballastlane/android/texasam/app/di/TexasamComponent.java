package com.ballastlane.android.texasam.app.di;

import com.ballastlane.android.baselibrary.app.di.AppScope;
import com.ballastlane.android.texasam.data.managers.remote.base.ApiServiceInterface;


import dagger.Component;


/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/26/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */

@AppScope
@Component(modules = {ApiServicesModule.class})
public interface TexasamComponent {
    ApiServiceInterface serviceInterfaceApi();
  //  ApiServiceInterface.Auth serviceInterfaceAuthApi();
}
