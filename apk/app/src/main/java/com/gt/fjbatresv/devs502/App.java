package com.gt.fjbatresv.devs502;

import android.app.Application;
import android.content.SharedPreferences;

import com.gt.fjbatresv.devs502.firebase.DI.FirebaseModule;
import com.gt.fjbatresv.devs502.lib.DI.LibsModule;
import com.gt.fjbatresv.devs502.main.DI.DaggerMainComponent;
import com.gt.fjbatresv.devs502.main.DI.MainComponent;
import com.gt.fjbatresv.devs502.main.DI.MainModule;
import com.gt.fjbatresv.devs502.main.ui.MainActivity;
import com.gt.fjbatresv.devs502.main.ui.MainView;


import java.util.HashMap;

/**
 * Created by javie_000 on 8/29/2016.
 */
public class App extends Application {
    private AppModule appModule;
    private LibsModule libsModule;
    private FirebaseModule firebaseModule;
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
        firebaseModule = new FirebaseModule();
    }

    //Components injection
    public MainComponent main(MainView view, MainActivity activity){
        return DaggerMainComponent.builder()
                .appModule(appModule)
                .libsModule(libsModule)
                .firebaseModule(firebaseModule)
                .mainModule(new MainModule(view))
                .build();
    }
}
