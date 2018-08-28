package com.ballastlane.android.texasam.data.managers.remote.base;


import com.ballastlane.android.baselibrary.model.BaseServiceRest;
/**
 * @author Daniela Perez danielaperez@kogimobile.com on 5/26/17.
 */

public class RestManagerPrivateService extends BaseServiceRest<ApiServiceInterface.Private> {

    private static final String IMAGE_PART = "signature";
    private static final String MULTI_PART_FORM_DATA = "multipart/form-data";

    /**
     * @param apiServiceInterface
     * @deprecated
     */
    public RestManagerPrivateService(ApiServiceInterface.Private apiServiceInterface) {
        super(apiServiceInterface);
    }


//    public Observable<Void> sendDeviceToken(String devicetoken) {
//        return (getApiService()).sendDeviceToken(devicetoken)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public Observable<Void> removeDeviceToken(String devicetoken) {
//        return (getApiService()).removeDeviceToken(devicetoken)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public Observable<User> getUser() {
//        return (getApiService()).getUser()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }



//    public Observable<RestUser> updateUser(UserRequest userRequest) {
//        return (getApiService()).updateUser(userRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }



}
