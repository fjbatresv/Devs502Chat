package com.gt.fjbatresv.devs502.main;

import com.google.firebase.auth.FirebaseUser;
import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.main.events.MainEvent;
import com.gt.fjbatresv.devs502.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by javie on 28/03/2017.
 */

public class MainPresenterImp implements MainPresenter {
    private EventBus bus;
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImp(EventBus bus, MainView view, MainInteractor interactor) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        bus.unRegister(this);
    }

    @Override
    @Subscribe
    public void onEvent(MainEvent event) {
        if (event.getTipo() == MainEvent.sendMessage){
            view.loadinSending(false);
            if (!event.getError().isEmpty() && event.getError() != null){
                view.showToast(event.getError());
            }else{
                view.sendMessage();
            }
        }else{
            view.loading(false);
            if (event.getError() != null && !event.getError().isEmpty()){
                view.showToast(event.getError());
            }else{
                switch (event.getTipo()){
                    case MainEvent.loadMessage:
                        view.loadMessages((Message) event.getObject());
                        break;
                    case MainEvent.loadUser:
                        view.loadUser((FirebaseUser) event.getObject());
                        break;
                    case MainEvent.logOut:
                        view.logout();
                        break;
                }
            }
        }
    }

    @Override
    public void loadMessage() {
        view.loading(true);
        interactor.loadMessage();
    }

    @Override
    public void unSubMessages() {
        view.loading(true);
        interactor.unSubMessages();
    }

    @Override
    public void loadUser() {
        view.loading(true);
        interactor.loadUser();
    }

    @Override
    public void sendMessage(String msg) {
        view.loadinSending(true);
        interactor.sendMessage(msg);
    }

    @Override
    public void logout() {
        view.loading(true);
        interactor.logout();
    }
}
