package yenilab.co.apistartup.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import yenilab.co.apistartup.BuildConfig;
import yenilab.co.apistartup.model.response.OAuthResponse;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class OAuth implements Serializable {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("grant_type")
    private String grantType;
    @SerializedName("scope")
    private String scope;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;

    //Application Storage File Name and Key Name
    private static final String OAUTH_PREFS_NAME = "OAUTH_PREF";
    private static final String OAUTH_PREFS_KEY = "OAUTH_KEY";
    private static final String OAUTH_GRANT_TYPE = "password";
    private static final String OAUTH_SCOPE = "*";

    public OAuth(String username, String password) {

        this.username = username;
        this.password = password;
        this.grantType = OAUTH_GRANT_TYPE;
        this.scope = OAUTH_SCOPE;
        this.clientId = BuildConfig.CLIENT_ID;
        this.clientSecret = BuildConfig.CLIENT_KEY;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getGrantType() {

        return grantType;
    }

    public void setGrantType(String grantType) {

        this.grantType = grantType;
    }

    public String getScope() {

        return scope;
    }

    public void setScope(String scope) {

        this.scope = scope;
    }

    public String getClientId() {

        return clientId;
    }

    public void setClientId(String clientId) {

        this.clientId = clientId;
    }

    public String getClientSecret() {

        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {

        this.clientSecret = clientSecret;
    }

    //Serialize OAuth model to String.
    private String serialize() {

        Gson gson = new Gson();
        return gson.toJson(this);

    }

    //Deserialize string to OAuth model.
    public static OAuth create(String serializedData) {

        Gson gson = new Gson();
        return gson.fromJson(serializedData, OAuth.class);

    }

    public static OAuth get(Context context) {

        SharedPreferences preferencesReader = context.getSharedPreferences(OAUTH_PREFS_NAME, Context.MODE_PRIVATE);
        String serializedData = preferencesReader.getString(OAUTH_PREFS_KEY, "");

        //If OAuth not exist return null
        if (serializedData.isEmpty())
            return null;

        OAuth oAuth = create(serializedData);

        return oAuth;

    }

    //To save OAuth data into Application storage
    public static void save(OAuth oAuth, Context context) {

        String serializedData = oAuth.serialize();

        SharedPreferences preferencesReader = context.getSharedPreferences(OAUTH_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesReader.edit();
        editor.putString(OAUTH_PREFS_KEY, serializedData);
        editor.commit();

    }

}
