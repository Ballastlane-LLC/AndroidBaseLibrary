package com.ballastlane.android.texasam.data.managers.remote.base;

import com.ballastlane.android.texasam.data.entities.User;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceInterface {

    interface Public {

        @GET("/users/{username}")
        Single<Response<ResponseBody>> checkEmail(@Path("username") String username);

        @POST("/users/auth")
        Observable<LogonResponse> logonUser(@Body LogonRequest userLogoFn);

    }

    interface Private {
        @GET("users/me")
        Observable<User> getUser();
    }

}
