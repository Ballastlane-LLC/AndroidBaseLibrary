package com.ballastlane.android.texasam.data.managers.remote.base;

import com.ballastlane.android.baselibrary.model.RestClientManager;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/24/18.
 */
public class RestClient {

    private static RestClient instance = null;
    private static RestClientManager restClientManager;

//    @NonNull
//    public static RestClient getInstance() {
//        if (instance == null) {
//            instance = new RestClient();
//            ServiceConfiguration<RestManagerPublicService, ApiServiceInterface> publicServiceConfiguration;
//            publicServiceConfiguration = new ServiceConfiguration<>(
//                    "http://172.23.10.45:1337/v1/",
//                    new ApiServiceFactory<>(RestManagerPublicService.class),
//                    ApiServiceInterface.class);
//
//            ServiceConfiguration<RestManagerPrivateService, ApiServiceInterface> privateServiceConfiguration =
//                    new ServiceConfiguration<>(
//                            "http://172.23.10.45:1337/v1/",
//                            new ApiServiceFactory<>(RestManagerPrivateService.class),
//                            ApiServiceInterface.class);
//
//            privateServiceConfiguration.setRequestInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    Request.Builder builder = (new Request.Builder()).url(request.url()).method(request.method(), request.body());
//                    String accessToken = PreferencesUtils.getUserToken();
//
//                    if (!accessToken.isEmpty()) {
//                        Header currentHeader = new Header("Authorization", String.format("%s", accessToken));
//                        builder.addHeader(currentHeader.name.utf8(), currentHeader.value.utf8());
//                    }
//                    return chain.proceed(builder.build());
//                }
//            });
//            restClientManager = new RestClientManager();
//            restClientManager.addServiceConfiguration(publicServiceConfiguration);
//            restClientManager.addServiceConfiguration(privateServiceConfiguration);
//        }
//        return instance;
//    }

    public static RestClientManager getRestClientManager() {
        return restClientManager;
    }
}