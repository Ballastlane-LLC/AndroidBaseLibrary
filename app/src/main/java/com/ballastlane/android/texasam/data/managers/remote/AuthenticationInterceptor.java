package com.ballastlane.android.texasam.data.managers.remote;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 4/26/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
public class AuthenticationInterceptor implements Interceptor {

    private Object object;

    public AuthenticationInterceptor(Object object) {
        this.object = object;
    }

    @Nullable
    @Override
    public Response intercept(@NonNull Chain chain) {
        Request.Builder requestBuilder;

        // TODO :: aqui seteas todo
        requestBuilder = chain.request().newBuilder().addHeader(
                "key", "value");
        try {
            return chain.proceed(requestBuilder.build());
        } catch (IOException e) {
            return null;
        }
    }
}


