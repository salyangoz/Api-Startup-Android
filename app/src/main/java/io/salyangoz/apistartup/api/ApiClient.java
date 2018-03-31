package io.salyangoz.apistartup.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import io.salyangoz.apistartup.BuildConfig;
import io.salyangoz.apistartup.model.Device;
import io.salyangoz.apistartup.model.OAuth;
import io.salyangoz.apistartup.model.Setting;
import io.salyangoz.apistartup.model.User;
import io.salyangoz.apistartup.model.response.OAuthResponse;
import io.salyangoz.apistartup.model.response.SettingResponse;

/**
 * Created by erkndeveci on 17/04/2017.
 */

public interface ApiClient {

    @POST(BuildConfig.API_VERSION + "/register")
    Call<Void> register(@Body User user);

    @POST("/oauth/token")
    Call<OAuthResponse> login(@Body OAuth oAuth);

    @POST("/oauth/token")
    Call<OAuthResponse> refreshToken(@Body OAuth oAuth, @Field("refresh_token") String refreshToken);

    @POST("/self/device")
    Call<Void> addDevice(@Body Device device);

    @POST("/self/setting")
    Call<Void> changeSetting(@Body Setting setting);

    @GET("/self/setting")
    Call<SettingResponse> settings();
}
