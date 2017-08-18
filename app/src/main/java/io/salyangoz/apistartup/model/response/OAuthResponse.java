package io.salyangoz.apistartup.model.response;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.salyangoz.apistartup.application.ApiStartupApplication;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class OAuthResponse implements Serializable {

    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;

    //Application Storage File Name and Key Name
    private static final String OAUTH_PREFS_NAME = "OAUTH_RESPONSE_PREF";
    private static final String OAUTH_PREFS_KEY = "OAUTH_RESPONSE_KEY";


    public String getTokenType() {

        return tokenType;
    }

    public void setTokenType(String tokenType) {

        this.tokenType = tokenType;
    }

    public int getExpiresIn() {

        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {

        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {

        return accessToken;
    }

    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;
    }

    public String getRefreshToken() {

        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {

        this.refreshToken = refreshToken;
    }

    //Serialize OAuth model to String.
    private String serialize() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }

    //Deserialize string to OAuth model.
    public static OAuthResponse create(String serializedData) {

        Gson gson = new Gson();
        return gson.fromJson(serializedData, OAuthResponse.class);

    }

    public static OAuthResponse get() {

        SharedPreferences preferencesReader = ApiStartupApplication.getAppContext().getSharedPreferences(OAUTH_PREFS_NAME, Context.MODE_PRIVATE);
        String serializedData = preferencesReader.getString(OAUTH_PREFS_KEY, "");

        //If OAuth not exist return null
        if (serializedData.isEmpty())
            return null;

        OAuthResponse oAuth = create(serializedData);

        return oAuth;

    }

    //To save OAuth data into Application storage
    public static void save(OAuthResponse oAuth) {

        String serializedData = oAuth.serialize();

        SharedPreferences preferencesReader = ApiStartupApplication.getAppContext().getSharedPreferences(OAUTH_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesReader.edit();
        editor.putString(OAUTH_PREFS_KEY, serializedData);
        editor.commit();

    }

}
