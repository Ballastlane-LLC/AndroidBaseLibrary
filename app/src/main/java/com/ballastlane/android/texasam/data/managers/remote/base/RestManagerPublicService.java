package com.ballastlane.android.texasam.data.managers.remote.base;

import com.ballastlane.android.baselibrary.model.BaseServiceRest;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 5/26/17.
 */

public class RestManagerPublicService extends BaseServiceRest<ApiServiceInterface> {

    /**
     * @param apiServiceInterface
     * @deprecated
     */


    public RestManagerPublicService(ApiServiceInterface apiServiceInterface) {
        super(apiServiceInterface);
    }


}
