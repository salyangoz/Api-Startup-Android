package yenilab.co.apistartup.api;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yenilab.co.apistartup.BuildConfig;
import yenilab.co.apistartup.application.ApiStartupApplication;
import yenilab.co.apistartup.model.APIError;
import yenilab.co.apistartup.model.OAuth;
import yenilab.co.apistartup.model.response.OAuthResponse;

/**
 * Created by erkndeveci on 17/04/2017.
 */

public class ApiClientProvider {

    private static ApiClient apiClient = null;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit;
    private static AuthenticationInterceptor authInterceptor;

    public static Retrofit getRetrofitInstance() {

        return retrofit;
    }

    public static ApiClient getInstance() {

        if (apiClient == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(loggingInterceptor);
            httpClient.addInterceptor(new HeaderInterceptor());

        }

        OAuthResponse oAuthResponse = OAuthResponse.get();
        if (oAuthResponse != null && authInterceptor == null) {

            authInterceptor = new AuthenticationInterceptor(oAuthResponse.getAccessToken());

            //Add Bearer Authentication if Authentication Token Exists
            if (!httpClient.interceptors().contains(authInterceptor)) {
                httpClient.authenticator(new TokenAuthenticator());
                httpClient.interceptors().add(authInterceptor);
            }

        }

        builder.client(httpClient.build()).build();
        retrofit = builder.build();
        retrofit.responseBodyConverter(APIError.class, new Annotation[0]);

        return retrofit.create(ApiClient.class);

    }


}
