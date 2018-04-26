package com.ballastlane.android.texasam.data.managers.remote.base;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/24/18.
 */

public class BaseService1<I> {

    public I getApiServiceInterface() {
        return apiServiceInterface;
    }

    void setApiServiceInterface(I apiServiceInterface) {
        this.apiServiceInterface = apiServiceInterface;
    }

    private I apiServiceInterface;

    /**
     * @deprecated use @see com.kogimobile.androidstudiobaselib.rest.ApiServiceFactory
     * @param apiServiceInterface
     */
    @Deprecated
    public BaseService1(I apiServiceInterface) {
        this.apiServiceInterface = apiServiceInterface;
    }

    public BaseService1(){

    }

    public I getApiService() {
        return apiServiceInterface;
    }

}
