package yenilab.co.apistartup.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import yenilab.co.apistartup.BuildConfig;

/**
 * Created by erkndeveci on 17/04/2017.
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .addHeader("Content-Type", "yenilab/co/apistartup/application/x-www-form-urlencoded")
                .addHeader("Accept", "yenilab/co/apistartup/application/json")
                .addHeader("Client-Id", BuildConfig.CLIENT_ID)
                .addHeader("Client-Secret", BuildConfig.CLIENT_KEY);

        Request request = builder.build();

        Response response = chain.proceed(request);

        return response;
    }
}
