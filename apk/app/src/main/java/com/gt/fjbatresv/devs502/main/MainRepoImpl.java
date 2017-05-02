package com.gt.fjbatresv.devs502.main;

import android.content.Context;

import com.gt.fjbatresv.devs502.firebase.FirebaseHelper;
import com.gt.fjbatresv.devs502.firebase.FirebaseListener;
import com.gt.fjbatresv.devs502.firebase.FirebaseSearchListener;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.main.events.MainEvent;

/**
 * Created by javie on 28/03/2017.
 */

public class MainRepoImpl implements MainRepo {
    private Context context;
    private EventBus bus;
    private FirebaseHelper helper;

    public MainRepoImpl(Context context, EventBus bus, FirebaseHelper helper) {
        this.context = context;
        this.bus = bus;
        this.helper = helper;
    }

    @Override
    public void loadMessage() {
        helper.subscribeMessages();
    }

    @Override
    public void loadUser() {
        helper.getUser(new FirebaseSearchListener() {
            @Override
            public void onSuccess(Object obj) {
                bus.post(new MainEvent(MainEvent.loadUser, obj));
            }

            @Override
            public void onError(String error) {
                bus.post(new MainEvent(MainEvent.loadUser, error));
            }
        });
    }

    @Override
    public void unSubMessages() {
        helper.unSubscribeMessages();
    }

    @Override
    public void logout() {
        helper.logOut(new FirebaseListener() {
            @Override
            public void onSuccess() {
                bus.post(new MainEvent(MainEvent.logOut));
            }

            @Override
            public void onError(String error) {
                bus.post(new MainEvent(MainEvent.logOut, error));
            }
        });
    }

    @Override
    public void sendMessage(String msg) {
        helper.sendMessage(msg, new FirebaseListener() {
            @Override
            public void onSuccess() {
                bus.post(new MainEvent(MainEvent.sendMessage));
            }

            @Override
            public void onError(String error) {
                bus.post(new MainEvent(MainEvent.sendMessage, error));
            }
        });
    }
}
