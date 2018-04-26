package com.ballastlane.android.texasam.data.repositories.user;

import android.support.annotation.NonNull;

import com.ballastlane.android.texasam.data.entities.User;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class RepositoryUser implements RepositoryUserDataSource {

    private static RepositoryUser instance = null;
    private static RestUserSource sourceRest;

    @NonNull
    public static RepositoryUser newInstance() {
        if (instance == null) {
            instance = new RepositoryUser();
        }
        return instance;
    }


    private RestUserSource getRestSource() {
        if (sourceRest == null) {
            sourceRest = RestUserSource.newInstance();
        }
        return sourceRest;
    }


    @Override
    public Single<Response<ResponseBody>> checkEmail(String username) {
        return getRestSource().checkEmail(username);
    }

    @Override
    public Observable<LogonResponse> logon(LogonRequest logonRequest) {
        return getRestSource().logon(logonRequest);
    }

    @Override
    public Observable<User> get(String userId) {
        return getRestSource().get(userId);
    }


    @Override
    public Observable<User> getUser() {
        return getRestSource().getUser();
    }

//    @Override
//    public Observable<User> updateUser(UserRequest userRequest) {
//        return getRestSource().updateUser(userRequest);
//    }


}
