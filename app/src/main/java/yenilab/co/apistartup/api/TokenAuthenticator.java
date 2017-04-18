package yenilab.co.apistartup.api;

import android.content.Context;

import com.squareup.otto.Bus;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import yenilab.co.apistartup.model.OAuth;
import yenilab.co.apistartup.model.response.OAuthResponse;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class TokenAuthenticator implements Authenticator {

    private OAuthResponse oAuthResponse;
    private OAuth oAuth;
    private Context context;

    TokenAuthenticator(Context context) {

        this.context = context;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {

        oAuthResponse = OAuthResponse.get(context);
        oAuth = OAuth.get(context);

        //Call refresh token service
        retrofit2.Response<OAuthResponse> oResponse = ApiClientProvider.getInstance(context)
                .refreshToken(oAuth, oAuthResponse.getRefreshToken())
                .execute();

        //Get OAuthResponse and Save To Application Storage
        oAuthResponse = oResponse.body();
        OAuthResponse.save(oAuthResponse, context);

        return response
                .request()
                .newBuilder()
                .header("Authorization", "Bearer" + oAuthResponse.getAccessToken())
                .build();

    }
}
