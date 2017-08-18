package io.salyangoz.apistartup.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by erkndeveci on 19/04/2017.
 */

public class Setting implements Serializable{

    @SerializedName("is_notification_on")
    private boolean isNotificationOn;

    public Setting(boolean isNotificationOn) {

        this.isNotificationOn = isNotificationOn;
    }

    public boolean isNotificationOn() {

        return isNotificationOn;
    }

    public void setNotificationOn(boolean notificationOn) {

        isNotificationOn = notificationOn;
    }
}
