package com.ballastlane.android.texasam.data.repositories.user;

import com.ballastlane.android.texasam.data.entities.User;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/24/18.
 */
public interface RepositoryUserDataSource {

    Observable<LogonResponse> logon(LogonRequest logonRequest);

    Single<Response<ResponseBody>> checkEmail(String username);

    Observable<User> get(String userId);

    Observable<User> getUser();



}

