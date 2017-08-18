package io.salyangoz.apistartup.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.salyangoz.apistartup.model.Setting;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class SettingResponse implements Serializable {

    @SerializedName("data")
    private Setting setting;

    public Setting getSetting() {

        return setting;
    }

    public void setSetting(Setting setting) {

        this.setting = setting;
    }
}
