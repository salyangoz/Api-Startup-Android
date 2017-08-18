package io.salyangoz.apistartup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class Device {

    public static final String PLATFORM = "android";
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("device_type")
    private String deviceType;

    public Device(String deviceId, String deviceType) {

        this.deviceId = deviceId;
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
