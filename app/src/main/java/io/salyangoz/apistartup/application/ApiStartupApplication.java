package io.salyangoz.apistartup.application;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class ApiStartupApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {

        super.onCreate();

        //Initialize Onesignal
        OneSignal.startInit(this).init();
        ApiStartupApplication.context = getApplicationContext();

    }

    public static Context getAppContext() {

        return ApiStartupApplication.context;
    }
}
