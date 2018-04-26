package com.ballastlane.ballastlanebaseapp.di.di;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServiceInterface {


    //Users

    @HEAD("/users/{username}")
    Single<Response<ResponseBody>> checkEmail(@Path("username") String username);


//    @PATCH("users/me")
//    Observable<User> updateUser(@Body UserRequest userRequest);



}
