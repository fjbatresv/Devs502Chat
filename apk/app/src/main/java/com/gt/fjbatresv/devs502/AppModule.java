package com.gt.fjbatresv.devs502;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.gt.fjbatresv.devs502.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie_000 on 8/29/2016.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    Application providesApplication(){
        return this.app;

    }

    @Singleton
    @Provides
    Context providesContext(){
        return this.app.getApplicationContext();
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(
                app.getLoggedUserSharedPreferences(),
                Context.MODE_PRIVATE
        );
    }


}
