package yenilab.co.apistartup.model.response;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

import yenilab.co.apistartup.application.ApiStartupApplication;
import yenilab.co.apistartup.model.Setting;

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
