package com.ballastlane.android.baselibrary.model;


import java.util.Collection;
import java.util.Hashtable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author kogiandroid on 1/14/16.
 *         Modified by Julian Cardona 04/19/16.
 *         Modified by Mariangela Salcedo 04/03/18 mariangelasalcedo@ballastlane.com)
 *         Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */
public class RestClientManager<T> {

    private Hashtable<Class, BaseServiceRest> apiServicesInstances = new Hashtable<>();

    public RestClientManager(ServiceConfiguration... serviceConfigurations) {
        setServiceConfigurations(serviceConfigurations);
    }

    public RestClientManager addServiceConfiguration(ServiceConfiguration serviceConfiguration) {
        initializeServiceConfiguration(serviceConfiguration);
        return this;
    }

    public T getService(Class<T> baseServiceClass) {
        return baseServiceClass.cast(apiServicesInstances.get(baseServiceClass));
    }

    public Collection<BaseServiceRest> getServices() {
        return apiServicesInstances.values();
    }

    public RestClientManager deleteService(Class serviceConfigurationClass) {
        apiServicesInstances.remove(serviceConfigurationClass);
        return this;
    }

    public RestClientManager deleteServiceConfigurations() {
        apiServicesInstances = new Hashtable<>();
        return this;
    }

    private void setServiceConfigurations(ServiceConfiguration[] serviceConfigurations) {
        apiServicesInstances = new Hashtable<>();
        for (ServiceConfiguration currentServiceConfiguration : serviceConfigurations) {
            initializeServiceConfiguration(currentServiceConfiguration);
        }
    }

    private void initializeServiceConfiguration(final ServiceConfiguration currentServiceConfiguration) {
        Retrofit retrofit = configRetrofit(currentServiceConfiguration);
        Object apiServiceInterface = retrofit.create(currentServiceConfiguration.getInterfaceClass());
        try {
            addApisServicesToRetrofit(currentServiceConfiguration, apiServiceInterface);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void addApisServicesToRetrofit(ServiceConfiguration currentServiceConfiguration,
                                           Object apiServiceInterface)
            throws IllegalAccessException,
            InstantiationException {

        BaseServiceRest apiService = (BaseServiceRest)
                currentServiceConfiguration
                .getApiServiceFactory()
                .factory();

        apiService.setApiServiceInterface(apiServiceInterface);

        apiServicesInstances.put(currentServiceConfiguration.getApiServiceClass(), apiService);
    }

    private Retrofit configRetrofit(ServiceConfiguration currentServiceConfiguration) {
        return new Retrofit.Builder()
                .baseUrl(currentServiceConfiguration.getBaseURL())
                .addConverterFactory(currentServiceConfiguration.getConverter())
                .client(currentServiceConfiguration.getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
