package fr.xebia.app.api;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.util.HashMap;
import java.util.Map;

@EBean(scope = EBean.Scope.Singleton)
public class ApiProvider {

    private RestAdapter mRestAdapter;

    private final Map<Class<?>, Object> mRetrofitServiceMap = new HashMap<Class<?>, Object>();

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        synchronized (mRetrofitServiceMap) {
            T service = (T) mRetrofitServiceMap.get(serviceClass);
            if (service == null) {
                service = mRestAdapter.create(serviceClass);
                mRetrofitServiceMap.put(serviceClass, service);
            }
            return service;
        }
    }


    @AfterInject
    void initRetrofit() {
        mRestAdapter = new RestAdapter.Builder()
                .setConverter(new JacksonConverter())
                .setEndpoint("http://backend.mobile.xebia.io/api/v1/blog")
                .build();

    }

}
