package com.gt.fjbatresv.devs502.main;

import com.gt.fjbatresv.devs502.main.events.MainEvent;

/**
 * Created by javie on 28/03/2017.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void onEvent(MainEvent event);
    void loadMessage();
    void unSubMessages();
    void loadUser();
    void sendMessage(String msg);
    void logout();
}
