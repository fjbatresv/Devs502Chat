package com.gt.fjbatresv.devs502.main.DI;

import com.gt.fjbatresv.devs502.AppModule;
import com.gt.fjbatresv.devs502.firebase.DI.FirebaseModule;
import com.gt.fjbatresv.devs502.lib.DI.LibsModule;
import com.gt.fjbatresv.devs502.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 29/03/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class, FirebaseModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
