package io.salyangoz.apistartup.api;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import io.salyangoz.apistartup.model.OAuth;
import io.salyangoz.apistartup.model.response.OAuthResponse;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class TokenAuthenticator implements Authenticator {

    private OAuthResponse oAuthResponse;
    private OAuth oAuth;

    TokenAuthenticator() {
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {

        oAuthResponse = OAuthResponse.get();
        oAuth = OAuth.get();

        //Call refresh token service
        retrofit2.Response<OAuthResponse> oResponse = ApiClientProvider.getInstance()
                .refreshToken(oAuth, oAuthResponse.getRefreshToken())
                .execute();

        //Get OAuthResponse and Save To Application Storage
        oAuthResponse = oResponse.body();
        OAuthResponse.save(oAuthResponse);

        return response
                .request()
                .newBuilder()
                .header("Authorization", "Bearer" + oAuthResponse.getAccessToken())
                .build();

    }
}
