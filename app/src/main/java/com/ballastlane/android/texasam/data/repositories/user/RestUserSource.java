package com.ballastlane.android.texasam.data.repositories.user;

import com.ballastlane.android.texasam.data.entities.User;
import com.ballastlane.android.texasam.data.managers.remote.base.ApiServiceInterface;
import com.ballastlane.android.texasam.data.managers.remote.base.RestClient;
import com.ballastlane.android.texasam.data.managers.remote.base.RestManagerPrivateService;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;
import com.ballastlane.android.texasam.utils.App;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

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
        return getPublicService()
                .checkEmail(username);
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


    private ApiServiceInterface getPublicService() {
        return App.texasamComponent.serviceInterfaceApi();
    }

    private RestManagerPrivateService getPrivateService() {
        return (RestManagerPrivateService) RestClient
                .getInstance()
                .getRestClientManager()
                .getService(RestManagerPrivateService.class);
    }

}
