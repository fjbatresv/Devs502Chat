package com.gt.fjbatresv.devs502.firebase.DI;

import android.content.Context;

import com.gt.fjbatresv.devs502.firebase.FirebaseHelper;
import com.gt.fjbatresv.devs502.lib.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 28/03/2017.
 */
@Module
class FirebaseModule {
    @Singleton
    @Provides
    FirebaseHelper providesFirebaseHelper(Context context, EventBus bus){
        return new FirebaseHelper(context, bus);
    }
}
