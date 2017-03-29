package com.gt.fjbatresv.devs502.main;

import android.content.Context;

import com.gt.fjbatresv.devs502.R;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.main.events.MainEvent;

/**
 * Created by javie on 28/03/2017.
 */

public class MainInteractorImpl implements MainInteractor {
    private Context context;
    private EventBus bus;
    private MainRepo repo;

    public MainInteractorImpl(Context context, EventBus bus, MainRepo repo) {
        this.context = context;
        this.bus = bus;
        this.repo = repo;
    }

    @Override
    public void loadMessage() {
        repo.loadMessage();
    }

    @Override
    public void loadUser() {
        repo.loadUser();
    }

    @Override
    public void unSubMessages() {
        repo.unSubMessages();
    }

    @Override
    public void sendMessage(String msg) {
        if (msg != null && !msg.isEmpty()){
            repo.sendMessage(msg);
        }else{
            bus.post(new MainEvent(MainEvent.sendMessage, context.getString(R.string.messageRequired)));
        }
    }

    @Override
    public void logout() {
        repo.logout();
    }
}
