package yenilab.co.apistartup.api;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
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

    public static ApiClient getInstance(final Context context) {

        if (apiClient == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(loggingInterceptor);
            httpClient.addInterceptor(new HeaderInterceptor());

        }

        OAuthResponse oAuthResponse = OAuthResponse.get(context);
        if (oAuthResponse != null) {

            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(oAuthResponse.getAccessToken());

            //Add Bearer Authentication if Authentication Token Exists
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.authenticator(new TokenAuthenticator(context));
                httpClient.interceptors().add(interceptor);
            }

        }

        builder.client(httpClient.build()).build();
        Retrofit retrofit = builder.build();

        return retrofit.create(ApiClient.class);

    }


}
