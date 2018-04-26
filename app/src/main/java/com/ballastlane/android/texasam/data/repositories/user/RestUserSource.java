package com.ballastlane.android.texasam.data.repositories.user;

import android.util.Log;

import com.ballastlane.android.texasam.R;
import com.ballastlane.android.texasam.data.entities.User;
import com.ballastlane.android.texasam.data.managers.remote.base.ApiServiceInterface;
import com.ballastlane.android.texasam.data.managers.remote.base.RestClient;
import com.ballastlane.android.texasam.data.managers.remote.base.RestManagerPrivateService;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;
import com.ballastlane.android.texasam.utils.App;

import java.util.Timer;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/24/18.
 */
public class RestUserSource implements RepositoryUserDataSource {

    private static RestUserSource instance;

    public static RestUserSource newInstance() {
        if (instance == null) {
            instance = new RestUserSource();
        }
        return instance;
    }

    @Override
    public Observable<LogonResponse> logon(LogonRequest logonRequest) {
        return getPublicService()
                .logonUser(logonRequest);
    }

    @Override
    public Single<Response<ResponseBody>> checkEmail(String username) {
        Log.d("test", App.texasamComponent.resources().getString(R.string.app_name));
        return getPublicService().checkEmail(username);
    }

    @Override
    public Observable<User> get(String userId) {
        return (getPrivateService())
                .getUser();
    }


    @Override
    public Observable<User> getUser() {
        return (getPrivateService())
                .getUser();
    }


    private ApiServiceInterface.Public getPublicService() {
        return App.texasamComponent.serviceInterfaceApi();
    }

    private ApiServiceInterface.Private getPrivateService() {
        return App.texasamComponent.serviceInterfaceAuthApi();

    }

}
