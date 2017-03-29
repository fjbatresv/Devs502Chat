package com.gt.fjbatresv.devs502;

import android.app.Application;
import android.content.SharedPreferences;

import com.gt.fjbatresv.devs502.lib.DI.LibsModule;


import java.util.HashMap;

/**
 * Created by javie_000 on 8/29/2016.
 */
public class App extends Application {
    private AppModule appModule;
    private LibsModule libsModule;
    private final static String LOGGED_USER_SHARED_PREFERENCES = "devs502";

    @Override
    public void onCreate() {
        super.onCreate();
        initModule();
    }

    //Getter
    public static String getLoggedUserSharedPreferences() {
        return LOGGED_USER_SHARED_PREFERENCES;
    }

    private void initModule() {
        appModule = new AppModule(this);
        libsModule = new LibsModule();
    }

    //Components injection

}
