package yenilab.co.apistartup.api;

import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import yenilab.co.apistartup.BuildConfig;
import yenilab.co.apistartup.model.Device;
import yenilab.co.apistartup.model.OAuth;
import yenilab.co.apistartup.model.Setting;
import yenilab.co.apistartup.model.User;
import yenilab.co.apistartup.model.response.OAuthResponse;
import yenilab.co.apistartup.model.response.SettingResponse;

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

    @POST(BuildConfig.API_VERSION + "/self/device")
    Call<Void> addDevice(@Body Device device);

    @POST(BuildConfig.API_VERSION + "/self/setting")
    Call<Void> changeSetting(@Body Setting setting);

    @GET(BuildConfig.API_VERSION + "/self/setting")
    Call<SettingResponse> settings();
}
