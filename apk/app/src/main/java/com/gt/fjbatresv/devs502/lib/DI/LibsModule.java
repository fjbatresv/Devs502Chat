package com.gt.fjbatresv.devs502.lib.DI;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gt.fjbatresv.devs502.lib.GlideImageLoader;
import com.gt.fjbatresv.devs502.lib.GreenRobotEventBus;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie_000 on 8/29/2016.
 */
@Module
public class LibsModule {
    public LibsModule() {
    }

    @Singleton
    @Provides
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus bus){
        return new GreenRobotEventBus(bus);
    }

    @Singleton
    @Provides
    org.greenrobot.eventbus.EventBus providesGreenRobot(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Singleton
    @Provides
    ImageLoader providesImageLoader(RequestManager manager){
        return new GlideImageLoader(manager);
    }

    @Singleton
    @Provides
    RequestManager providesRequestManager(Context context){
        return Glide.with(context);
    }

}
