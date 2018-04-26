package com.ballastlane.android.texasam.data.managers.remote.base;

import com.ballastlane.android.baselibrary.model.BaseService;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonRequest;
import com.ballastlane.android.texasam.data.managers.remote.logon.LogonResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 5/26/17.
 */

public class RestManagerPublicService extends BaseService<ApiServiceInterface> {

    /**
     * @param apiServiceInterface
     * @deprecated
     */


    public RestManagerPublicService(ApiServiceInterface apiServiceInterface) {
        super(apiServiceInterface);
    }


}
