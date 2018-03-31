package io.salyangoz.apistartup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class Device {

    public static final String PLATFORM = "android";
    @SerializedName("token")
    private String token;
    @SerializedName("device_type")
    private String deviceType;

    public Device(String token, String deviceType) {

        this.token = token;
        this.deviceType = deviceType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
