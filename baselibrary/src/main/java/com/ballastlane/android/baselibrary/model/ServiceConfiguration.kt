package com.ballastlane.android.baselibrary.model

import android.webkit.URLUtil
import com.ballastlane.android.baselibrary.R
import com.ballastlane.android.baselibrary.app.BaseApp
import com.ballastlane.android.baselibrary.utils.CollectionTypedAdapter
import com.ballastlane.android.baselibrary.utils.DoubleTypedAdapter
import com.ballastlane.android.baselibrary.utils.LongTypedAdapter

import com.google.gson.GsonBuilder

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Header
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author kogiandroid on 1/14/16.
 * Modified by Julian Cardona 04/19/16.
 * Modified by Mariangela Salcedo 04/03/18.
 * Use this class to put the custom configuration for the Services instances on your apps.
 * Feel free to subclass this for overwriting the getters on the app class in the way you prefer.
 * The type of the class should be the same as the ApiService
 */
class ServiceConfiguration<S : BaseService<I>, I>
/**
 *
 * @param baseURL
 * @param interfaceClass must be an interface
 */

/**
 *
 * @param baseURL must be a valid URL
 * @param apiServiceFactory must extend from the BaseService
 * @param apiInterfaceServiceFactory must be an interface
 */
(val baseURL: String, val apiServiceFactory: ApiServiceFactory<S>,
 apiInterfaceServiceClass: Class<I>) {
    val interfaceClass: Class<I>
    val apiServiceClass: Class<BaseService<I>>

    var converter = GsonConverterFactory.create(GsonBuilder()
            .registerTypeHierarchyAdapter(Collection::class.java, CollectionTypedAdapter())
            .registerTypeHierarchyAdapter(Long::class.java, LongTypedAdapter())
            .registerTypeHierarchyAdapter(Double::class.java, DoubleTypedAdapter())
            .setDateFormat(BaseApp.component.resources().getString(R.string.date_format_general))
            .excludeFieldsWithoutExposeAnnotation()
            .create())!!
    /**
     * Remember to subclass the ServiceConfiguration instance for runtime operations.
     * Suggested for this field
     * @return an arrayList of headers
     */
    /**
     * deprecated use setInterceptor instead
     * @param headers
     */
    @get:Deprecated("use setInterceptor instead")
    var headers = ArrayList<Header>()
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    /**
     *
     * @return by Default an OkClient
     */
    var client: OkHttpClient? = null
        get() {
            if (field == null) {
                val builder = OkHttpClient().newBuilder()
                builder.readTimeout(30, TimeUnit.SECONDS).addInterceptor(loggingInterceptor)
                if (requestInterceptor != null) {
                    builder.addInterceptor(requestInterceptor!!)
                }
                this.client = builder.build()
            }
            return field
        }
    var requestInterceptor: Interceptor? = null

    /**
     * Remember to subclass the ServiceConfiguration instance for runtime operations.
     * Suggested for this field
     * @return a log level for retrofit @see retrofit.RestAdapter.LogLevel
     */
    var logLevel: HttpLoggingInterceptor.Level
        get() = loggingInterceptor.level
        set(logLevel) {
            this.loggingInterceptor.level = logLevel
        }

    init {
        if (baseURL.isBlank()) {
            throw RuntimeException("url cannot be empty!!!")
        } else if (!URLUtil.isValidUrl(baseURL)) {
            throw RuntimeException("url is not valid!!!")
        }
        //            apiInterfaceService = apiInterfaceServiceFactory.factory();
        this.interfaceClass = apiInterfaceServiceClass as Class<I>
        if (!apiInterfaceServiceClass.isInterface) {
            throw RuntimeException("Interface is not an interface!!!")
        }
        try {
            apiServiceClass = apiServiceFactory.factory().javaClass
        } catch (e: IllegalAccessException) {
            throw RuntimeException("IllegalAccessException", e)
        } catch (e: InstantiationException) {
            throw RuntimeException("InstantiationException", e)
        }

    }


}
