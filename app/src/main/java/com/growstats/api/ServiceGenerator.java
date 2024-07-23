package com.growstats.api;

import android.text.TextUtils;
import android.util.Log;

import com.growstats.api.security.AuthenticationInterceptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServiceGenerator {

    private final static Converter.Factory converterFactory = JacksonConverterFactory.create();
    private final static OkHttpClient sharedClient;

    static  {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .pingInterval(20, TimeUnit.SECONDS)
                .build();
    }

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, ApiError> errorBodyConverter =
            (Converter<ResponseBody, ApiError>) converterFactory.responseBodyConverter(
                    ApiError.class, new Annotation[0], null);

    public static  <S> S createService(Class<S> serviceClass, String apiKey, String secret, String apiUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(converterFactory);

        if (TextUtils.isEmpty(apiKey) && TextUtils.isEmpty(secret)) {
            retrofitBuilder.client(sharedClient);
        } else {
            // `adaptedClient` will use its own interceptor, but share thread pool etc with the 'parent' client
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(apiKey, secret);
            OkHttpClient adaptedClient = sharedClient.newBuilder().addInterceptor(interceptor).build();
            retrofitBuilder.client(adaptedClient);
        }

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            Log.i(ServiceGenerator.class.getName().toString(), response.raw().toString());
            if (response.isSuccessful()) {
                return response.body();
            } else {
                Log.e(ServiceGenerator.class.getName().toString(),response.toString());
                ApiError apiError = getApiError(response);
                throw new ApiException(apiError);
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public static ApiError getApiError(Response<?> response) throws IOException, ApiException {
        return errorBodyConverter.convert(response.errorBody());
    }
}


