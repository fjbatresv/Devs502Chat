package com.gt.fjbatresv.devs502.firebase.DI;

import com.gt.fjbatresv.devs502.AppModule;
import com.gt.fjbatresv.devs502.lib.DI.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javie on 28/03/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class,FirebaseModule.class})
public interface FirebasComponent {
}
