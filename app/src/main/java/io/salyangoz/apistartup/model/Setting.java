package io.salyangoz.apistartup.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by erkndeveci on 19/04/2017.
 */

public class Setting implements Serializable{

    @SerializedName("is_notification_on")
    private int isNotificationOn;

    public Setting(int isNotificationOn) {

        Log.d("model", String.valueOf(isNotificationOn));
        this.isNotificationOn = isNotificationOn;
    }

    public int isNotificationOn() {

        return isNotificationOn;
    }

    public void setNotificationOn(int notificationOn) {

        isNotificationOn = notificationOn;
    }
}
